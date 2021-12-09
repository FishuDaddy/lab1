/**
 * Interface for an Object which is both coordinate and direction dependent.
 */
public interface Rotatable extends Mappable {
    void setDirection(double direction);
    double getDirection();
}
