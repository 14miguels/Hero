import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalPosition;

public class Coin extends Element {

    // Constructor to initialize the position of the Coin
    public Coin(int x, int y) {
        super(x, y);
    }

    // Implement the draw method for Coin
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00")); // Yellow color for coins
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "$"); // "$" symbol for coins
    }
}



