import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalPosition;

public class Hero extends Element {

    // Constructor to initialize the hero's position
    public Hero(int x, int y) {
        super(x, y);
    }

    // Implement the draw method for Hero
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00")); // Green color for the hero
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "@"); // "@" symbol for the hero
    }

    // Move the hero up
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    // Move the hero down
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    // Move the hero left
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    // Move the hero right
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    // Setter for updating the hero's position
    public void setPosition(Position position) {
        this.position = position;
    }
}

