/**
 * Interface for an Object which is both coordinate and direction dependent.
 */
public interface DirectionAndCoordinateDependent extends CoordinateDependent {
    void setDirection(double direction);
    double getDirection();
}
