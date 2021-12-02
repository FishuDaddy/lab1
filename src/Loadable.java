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

    /**
     * Constructor for Loadable
     * @param capacity the maximum capacity of Transportables the Loadable can posses.
     * @param loadThreshold the threshold which limits how close/far away a Transportable has to be in order to be added to the Loadable.
     * @param maxWeight the maximum weight the Loadable can carry.
     * @param isOrderDependent whether the Transportables should be able to be removed in only the reverse order that they were added or freely.
     */
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

    /**
     * Checks if Loadable is at capacity.
     * @return true if not full, false if full.
     */
    private boolean isNotFull() {
        return currentTransportedVehicles < capacity;
    }

    private boolean isNotTooHeavy(int weight) {
        return getWeight() + weight <= maxWeight;
    }

    public int getWeight() {
        return transportedWeight;
    }

    /**
     * The conditions that have to apply for a Loadable to accept a Transportable.
     * @param goal the object containing the Loadable.
     * @param target the Transportable object to be loaded.
     * @return true if conditions met, false if not.
     * @throws Exception if a condition is not met.
     */
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

    /**
     * Calculates and sets the variables for loading.
     * @param target the target to be loaded onto the Loadable.
     */
    protected void calculateLoad(Transportable target) {
        transportedWeight = transportedWeight + target.getWeight();
        currentTransportedVehicles = currentTransportedVehicles + 1;
        target.setBeingTransportState(true);
        onTransport.add(target);
    }

    /**
     * Calculates and sets the variables for unloading.
     * @param target the target to be unloaded from the Loadable.
     */
    private void calculateUnload(Transportable target) {
        transportedWeight = transportedWeight - target.getWeight();
        currentTransportedVehicles = currentTransportedVehicles - 1;
        target.setBeingTransportState(false);
        onTransport.remove(target);
    }

    /**
     * Unloads the Transportable from the Loadable.
     * @param loaded the loaded Transportable.
     * @throws Exception if the Transportable is not loaded onto the current Loadable.
     */
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
