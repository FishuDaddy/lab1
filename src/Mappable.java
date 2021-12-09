/**
 * Defines an interface for Objects which are dependendt on coordinates to work correctly.
 */
public interface Mappable {
    void setCoordinates(double x, double y);
    double getX();
    double getY();
}
