import java.lang.reflect.Type;

public class Workshop implements CoordinateDependent {
    double x;
    double y;
    Loadable loadable;
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Workshop(double x, double y, int threshold, int capacity, int maxWeight) {
        this.x = x;
        this.y = y;
        loadable = new Loadable(capacity, threshold, maxWeight, false);
    }
    public void load(Type<t> transportable)
}
