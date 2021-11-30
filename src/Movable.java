/**
 * Interface for implementation of methods used for movement
 */

public interface Movable extends CoordinateDependent {
    void move();
    void turnLeft();
    void turnRight();
}
