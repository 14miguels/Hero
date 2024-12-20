import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game(){
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            arena = new Arena(40, 20); // Create Arena object
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics()); // Draw arena elements
        screen.refresh(); // Refresh screen
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key); // Process user input
    }

    public void run() throws IOException, InterruptedException {
        while (true) {
            draw();  // Draw the current state of the arena

            KeyStroke key = screen.readInput();  // Read user input

            if (key == null) {
                continue;  // If no key is pressed, continue the loop
            }

            processKey(key);  // Process the input (move hero, etc.)

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                System.out.println("Game quit by user.");
                break;  // Exit the game loop if "q" is pressed
            }

            if (arena.getCoinsRemaining() == 0) {
                System.out.println("You collected all the coins! Game Over!");
                break;  // Exit the game loop if all coins are collected
            }
        }
        stop();  // Stop the screen session
    }

    private void stop() throws IOException {
        if (screen != null) {
            screen.stopScreen(); // Stop the screen properly
        }
    }
}
