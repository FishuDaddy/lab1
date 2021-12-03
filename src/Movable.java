/**
 * Interface for implementation of methods used for movement
 */

public interface Movable extends DirectionAndCoordinateDependent {
    /**
     * Method for moving the Movable.
     */
    void move();

    /**
     * Method for turning the Movable left.
     */
    void turnLeft();

    /**
     * Method for turning the Movable right.
     */
    void turnRight();
}
