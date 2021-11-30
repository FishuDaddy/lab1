import java.util.ArrayList;
import java.util.List;

abstract class CarTransport extends MotorVehicle {
    protected int transportedWeight;
    protected int maxWeight;
    protected int capacity;
    protected int currentTransportedVehicles;
    protected int loadThreshold;
    protected boolean isStationary;
    List<Transportable> onTransport;

    protected int getTransportedWeight() {
        return transportedWeight;
    }
    protected int getMaxWeight() {
        return maxWeight;
    }
    protected int getCapacity() {
        return capacity;
    }
    protected int getThreshold() {
        return loadThreshold;
    }
    protected int getCurrentTransportedVehicles() {
        return currentTransportedVehicles;
    }

    public boolean isStationary() {
        return isStationary;
    }

    public int netWeight() {
        return  weight + transportedWeight;
    }
    protected void transportAssembler(int maxWeight, int capacity, int loadThreshold) {
        this.maxWeight = maxWeight;
        this.capacity = capacity;
        this.loadThreshold = loadThreshold;
        this.isStationary = true;
        onTransport = new ArrayList<>();
    }

    public boolean canBeLoaded(Transportable target) {
        if (isWithinThreshold(target)) {
            if (isNotFull()) {
                return isNotTooHeavy(target.getWeight());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the target is within 10 coordinates x and y.
     * @param target the target which coordinates is compared to the location of the transport.
     * @return true if the condition is met, false if not.
     */
    private boolean isWithinThreshold(Transportable target) {
        if (target.getX() <= this.getX() + this.loadThreshold  || target.getX() >= this.getX() + this.loadThreshold) {
            return target.getY() <= this.getX() + this.loadThreshold || target.getY() >= this.getY() + this.loadThreshold;
        } else {
            return false;
        }
    }
    private boolean isNotFull() {
        return currentTransportedVehicles < capacity;
    }
    private boolean isNotTooHeavy(int weight) {
        return netWeight() + weight <= maxWeight;
    }

    public void load(Transportable loadable) {
        if (!onTransport.contains(loadable)) {
            calculateLoad(loadable);
        }
    }

    private void calculateLoad(Transportable loadable) {
        transportedWeight = transportedWeight + loadable.getWeight();
        currentTransportedVehicles = currentTransportedVehicles + 1;
        onTransport.add(loadable);
    }

    public void unload(Transportable loaded) throws Exception {
        if (onTransport.contains(loaded)) {
            calculateUnload(loaded);
        } else {
            throw new Exception("Error, vehicle not on transport");
        }
    }

    private void calculateUnload(Transportable loadable) {
        transportedWeight = transportedWeight - loadable.getWeight();
        currentTransportedVehicles = currentTransportedVehicles - 1;
        onTransport.remove(loadable);
    }

    double speedFactor() {
        if (!isStationary) {
            return enginePower * 0.3 * ((double)(maxWeight/(weight + transportedWeight)));
        } else {
            return 0; // Makes it so that incrementSpeed returns 0
        }
    }

}

