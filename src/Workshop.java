public class Workshop<T extends Transportable> implements CoordinateDependent {
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
    public void load(T target) throws Exception {
        System.out.println(target.getClass());
        System.out.println(type);
        if (loadable.loadableConditionsMet(this, target)) {
            loadable.calculateLoad(target);
        }
    }
    public void unload(Transportable target) throws Exception {
        loadable.unload(target);
    }
}
