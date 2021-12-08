/**
 * Interface for implementation of methods used for movement
 */

public interface Movable extends Rotatable {
    /**
     * Method for moving the Movable.
     */
    void move() throws Exception;

    /**
     * Method for turning the Movable left.
     */
    void turnLeft();

    /**
     * Method for turning the Movable right.
     */
    void turnRight();
}
