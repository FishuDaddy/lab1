/**
 * An object which can accept instances of Transportable
 * @param <T> a sub-object of Transportable which can enter the workshop.
 */
public class Workshop<T extends Transportable> implements Mappable {
    private int x;
    private int y;
    private Position pos;
    private Loadable loadable;
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    /**
     * Sets the coordinates of the Workshop-
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public void setCoordinates(double x, double y) {
        pos.setCoordinates(x, y);
    }
    public Workshop(int x, int y, int threshold, int capacity, int maxWeight) {
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

    /**
     * Returns the amount of vehicles inside the Workshop.
     * @return amount of vehicles in workshop.
     */
    public int getInWorkshop() {
        return(loadable.getOnTransport());
    }

    /**
     * Unloads the target from the Workshop.
     * @param target the Transportable to be unloaded.
     * @throws Exception if the target does not fulfill the conditions to be unloaded.
     */
    public void unload(T target) throws Exception {
        loadable.unload(target);
    }
}
