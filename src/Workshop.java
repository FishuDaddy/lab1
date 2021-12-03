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

    /**
     * Loads the target into the Workshop.
     * @param target the Transportable to be loaded.
     * @throws Exception if the target does not fulfill the conditions to be loaded.
     */
    public void load(T target) throws Exception {
        if (loadable.loadableConditionsMet(this, target)) {
            loadable.calculateLoad(target);
        }
    }

    public int getInWorkshop() {
        return(loadable.getOnTransport());
    }

    /**
     * Unloads the target from the Worshop.
     * @param target the Transportable to be unloaded.
     * @throws Exception if the target does not fulfill the conditions to be unloaded.
     */
    public void unload(Transportable target) throws Exception {
        loadable.unload(target);
    }
}
