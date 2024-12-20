import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private List<Wall> walls;  // List of Elements (Wall or Hero)
    private List<Coin> coins;
    private int width;
    private int height;
    private Hero hero;

    // Constructor
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(width / 2, height / 2);  // Hero starting position
        this.walls = createWalls();  // Initialize elements (walls and hero)
        this.coins = new ArrayList<>();
        createCoins();

    }

    private void createCoins() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x, y;
            do {
                x = random.nextInt(width - 2) + 1;
                y = random.nextInt(height - 2) + 1;
            } while (!isPositionOccupied(x, y));
            coins.add(new Coin(x, y));
        }
    }

    private boolean isPositionOccupied(int x, int y) {
        for (Wall wall : walls) {
            if (wall.getPosition().getX() == x && wall.getPosition().getY() == y) {
                return true;
            }
        }
        for (Coin coin : coins) {
            if (coin.getPosition().getX() == x && coin.getPosition().getY() == y) {
                return true;
            }
        }
        return hero.getPosition().getX() == x && hero.getPosition().getY() == y;
    }

    // Process key inputs to move the hero
    public void processKey(KeyStroke key) {
        Position newPosition = null;

        if (key.getKeyType() == KeyType.ArrowUp) {
            newPosition = hero.moveUp();
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            newPosition = hero.moveDown();
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            newPosition = hero.moveLeft();
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            newPosition = hero.moveRight();
        }

        if (newPosition != null && canHeroMove(newPosition)) {
            moveHero(newPosition);
            retrieveCoins();
        }
    }

    // Move the hero to the new position
    private void moveHero(Position position) {
        hero.setPosition(position);
    }

    // Check if the hero can move to the given position
    private boolean canHeroMove(Position position) {
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
            return false;
        }
        for (Wall wall : walls) {
            if (wall.getPosition().getX() == position.getX() && wall.getPosition().getY() == position.getY()) {
                return false; // Collides with a wall
            }
        }
        return true;
    }

    private void retrieveCoins() {
        List<Coin> collectedCoins = new ArrayList<>();
        for (Coin coin : coins) {
            if (coin.getPosition().equals(hero.getPosition())) {
                collectedCoins.add(coin);
            }
        }
        coins.removeAll(collectedCoins);
    }

    // Draw the elements (walls and hero) on the screen
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        for (Coin coin : coins) {
            coin.draw(graphics);
        }

        hero.draw(graphics);
    }

    public int getCoinsRemaining() {
        return coins.size(); // Return the number of coins left
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        // Top and bottom walls
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));            // Top wall
            walls.add(new Wall(c, height - 1));  // Bottom wall
        }

        // Left and right walls
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));           // Left wall
            walls.add(new Wall(width - 1, r));   // Right wall
        }

        return walls;
    }
}
