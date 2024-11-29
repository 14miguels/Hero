import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
        } catch (IOException e) {
            System.err.println("Failed to initialize the terminal: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(10, 10, new TextCharacter('X')); // Draw 'X' at (10,10)
        screen.refresh();
    }

    public void run() throws IOException, InterruptedException {
        draw(); // Call the draw method
        Thread.sleep(5000); // Keep the screen visible for 5 seconds
        stop(); // Ensure resources are cleaned up
    }


    private void stop() throws IOException {
        if (screen != null) {
            screen.stopScreen(); // Stop the screen
        }

    }
}

