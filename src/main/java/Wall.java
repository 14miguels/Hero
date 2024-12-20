import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalPosition;

public class Wall extends Element {

    // Constructor to initialize position of the wall
    public Wall(int x, int y) {
        super(x, y); // Call the parent constructor to set the position
    }

    // Implement the draw method for Wall without @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000")); // Red color for walls
        graphics.putString(new TerminalPosition(this.getPosition().getX(), this.getPosition().getY()), "#");
    }
    //sigma sigma boy
}
