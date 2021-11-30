import java.util.ArrayList;
import java.util.List;

abstract class CarTransport extends MotorVehicle {
    protected int transportedWeight;
    protected int maxWeight;
    protected int capacity;
    protected int currentTransportedVehicles;
    protected boolean isStationary;
    List<Transportable> onTransport;

    public int getTransportedWeight() {
        return transportedWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentTransportedVehicles() {
        return currentTransportedVehicles;
    }

    public boolean isStationary() {
        return isStationary;
    }

    public int netWeight() {
        return  weight + transportedWeight;
    }
    protected void transportAssembler(int maxWeight, int capacity) {
        this.maxWeight = maxWeight;
        this.capacity = capacity;
        this.isStationary = true;
        onTransport = new ArrayList<>();
    }

    public boolean canBeLoaded(int weight) {
        if (isNotFull()){
            return isNotTooHeavy(weight);
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

