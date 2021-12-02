public class Workshop<T> implements CoordinateDependent {
    private double x;
    private double y;
    private Loadable loadable;
    private T type;
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
    public void load(Transportable target) throws Exception {
        if (type == target.getClass()) {
            if (loadable.loadableConditionsMet(this, target)) {
                loadable.calculateLoad(target);
            }
        } else {
            throw new Exception("Invalid car model");
        }
    }
    public void unload(Transportable target) throws Exception {
        loadable.unload(target);
    }
}
