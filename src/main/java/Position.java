import java.util.Objects;

public class Position {
    private int x;
    private int y;

    // Constructor to set initial x and y coordinates
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter for X-coordinate
    public int getX() {
        return x;
    }

    // Setter for X-coordinate
    public void setX(int x) {
        this.x = x;
    }

    // Getter for Y-coordinate
    public int getY() {
        return y;
    }

    // Setter for Y-coordinate
    public void setY(int y) {
        this.y = y;
    }

    // Equals method to compare two Position objects
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }

    // Hash code method for hashing Position objects
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

