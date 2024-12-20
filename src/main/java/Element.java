import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {

    public Position position;

    // Constructor to set the position
    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    // Getter for position
    public Position getPosition() {
        return position;
    }

    // Abstract method that will be implemented by subclasses
    public abstract void draw(TextGraphics graphics);
}
