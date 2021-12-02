/**
 * Interface for implementation of methods used for movement
 */

public interface Movable extends DirectionAndCoordinateDependent {
    void move();
    void turnLeft();
    void turnRight();
}
