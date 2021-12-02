import java.lang.reflect.Type;
import java.util.List;

public class Loadable {

    protected int capacity;
    protected int currentTransportedVehicles;
    protected int loadThreshold;
    protected int transportedWeight;
    private final boolean isOrderDependent;
    private final int maxWeight;
    List<Transportable> onTransport;

    public Loadable(int capacity, int loadThreshold, int maxWeight, boolean isOrderDependent) {
        this.capacity = capacity;
        this.loadThreshold = loadThreshold;
        this.maxWeight = maxWeight;
        this.isOrderDependent = isOrderDependent;
    }


    /**
     * Checks if the target is within 10 coordinates x and y of goal.
     * @param goal is a variable belonging to an object which contains the Loadable object.
     * @param target the target which coordinates is compared to the location of the transport.
     * @return true if the condition is met, false if not.
     */
    private boolean isWithinThreshold(CoordinateDependent goal, Transportable target) {
        if (target.getX() <= goal.getX() + this.loadThreshold  || target.getX() >= goal.getX() - this.loadThreshold) {
            return target.getY() <= goal.getX() + this.loadThreshold || target.getY() >= goal.getY() - this.loadThreshold;
        } else {
            return false;
        }
    }

    private boolean isNotFull() {
        return currentTransportedVehicles < capacity;
    }

    private boolean isNotTooHeavy(int weight) {
        return getWeight() + weight <= maxWeight;
    }

    public int getWeight() {
        return transportedWeight;
    }

    protected boolean loadableConditionsMet(CoordinateDependent goal, Transportable target) throws Exception {
        if (target.canBeLoaded()) {
            if (isWithinThreshold(goal, target)) {
                if (isNotFull()) {
                    if (isNotTooHeavy(target.getWeight())){
                        return true;
                    } else {
                        throw new Exception("The Loadable would be too heavy if Transportable was loaded");
                    }
                } else {
                    throw new Exception("The Loadable is full.");
                }
            } else throw new Exception("Transportable is too far away from Loadable to be loaded.");
        } else {
            throw new Exception("Transportable cannot be loaded at the moment.");
        }
    }
    protected void calculateLoad(Transportable loadable) {
        transportedWeight = transportedWeight + loadable.getWeight();
        currentTransportedVehicles = currentTransportedVehicles + 1;
        loadable.setBeingTransportState(true);
        onTransport.add(loadable);
    }
    private void calculateUnload(Transportable loadable) {
        transportedWeight = transportedWeight - loadable.getWeight();
        currentTransportedVehicles = currentTransportedVehicles - 1;
        loadable.setBeingTransportState(false);
        onTransport.remove(loadable);
    }
    public void unload(Transportable loaded) throws Exception {
        if (0 < onTransport.size()) {
            if (isOnTransport(loaded)) {
                if (!isOrderDependent || onTransport.get(onTransport.size() - 1) == loaded) {
                    calculateUnload(loaded);

                } else {
                    throw new Exception("Error, vehicle is on Loadable but not accessible.");
                }
            } else {
                throw new Exception("Error, Transportable is not on Loadable.");
            }
        } else {
            throw new Exception("Error, Loadable does not contain any Transportables.");
        }
    }
    public boolean isOnTransport(Transportable target) {
        return onTransport.contains(target);
    }
}
