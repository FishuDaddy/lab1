import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class TransportVehicle extends MotorVehicle {
    protected int transportedWeight;
    protected int maxWeight;
    protected int capacity;
    protected int currentTransportedVehicles;
    protected int loadThreshold;
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

    public int netWeight() {
        return  weight + transportedWeight;
    }
    protected void transportAssembler(int maxWeight, int capacity, int loadThreshold, boolean allowedToMove) {
        this.maxWeight = maxWeight;
        this.capacity = capacity;
        this.loadThreshold = loadThreshold;
        this.allowedToMove = allowedToMove;
        onTransport = new ArrayList<>();
    }

    @Override
    public void move(){
        this.x += getCurrentSpeed() * Math.cos(Math.toRadians(this.dir));
        this.y += getCurrentSpeed() * Math.sin(Math.toRadians(this.dir));
        for (Transportable transported : onTransport) {
            transported.setCoordinates(this.x, this.y);
        }
    }

    @Override
    public void turnLeft(){
        this.dir += 45;
        this.dir %= 360;
        for (Transportable transported : onTransport) {
            transported.setDirection(this.getDirection());
        }
    }
    @Override
    public void turnRight() {
        this.dir -= 45;
        this.dir %= 360;
        for (Transportable transported : onTransport) {
            transported.setDirection(this.getDirection());
        }
    }

    public boolean isOnTransport(Transportable target) {
        return onTransport.contains(target);
    }

    public boolean canLoad(Transportable target) throws Exception {
        if (modelSpecificConditionsMet(target)) {
            return genericConditionsMet(target);
        } else {
            return false;
        }
    }

    private boolean genericConditionsMet(Transportable target) throws Exception {
        if (target.canBeLoaded()) {
            if (isStationary()) {
                if (isWithinThreshold(target)) {
                    if (isNotFull()) {
                        if (isNotTooHeavy(target.getWeight())){
                            return true;
                        } else {
                            throw new Exception("The TransportVehicle would be too heavy if Transportable was loaded");
                        }
                    } else {
                        throw new Exception("The TransportVehicle is full.");
                    }
                } else throw new Exception("Transportable is too far away from TransportVehicle to be loaded.");
            } else {
                throw new Exception("The TransportVehicle is not stationary.");
            }
        } else {
            throw new Exception("Transportable cannot be loaded at the moment.");
        }
    }

    protected abstract boolean modelSpecificConditionsMet(Transportable target);

    /**
     * Checks if the target is within 10 coordinates x and y.
     * @param target the target which coordinates is compared to the location of the transport.
     * @return true if the condition is met, false if not.
     */
    private boolean isWithinThreshold(Transportable target) {
        if (target.getX() <= this.getX() + this.loadThreshold  || target.getX() >= this.getX() - this.loadThreshold) {
            return target.getY() <= this.getX() + this.loadThreshold || target.getY() >= this.getY() - this.loadThreshold;
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

    public void load(Transportable loadable) throws Exception {
        if (canLoad(loadable)) {
            calculateLoad(loadable);
        }
    }

    private void calculateLoad(Transportable loadable) {
        transportedWeight = transportedWeight + loadable.getWeight();
        currentTransportedVehicles = currentTransportedVehicles + 1;
        loadable.setBeingTransportState(true);
        onTransport.add(loadable);
    }

    public void unload(Transportable loaded) throws Exception {
        if (0 < onTransport.size()) {
            if (isOnTransport(loaded)) {
                if (onTransport.get(onTransport.size() - 1) == loaded) {
                    calculateUnload(loaded);

                } else {
                    throw new Exception("Error, vehicle is on transport but not accessible.");
                }
            } else {
                throw new Exception("Error, Transportable is not on TransportVehicle.");
            }
        } else {
            throw new Exception("Error, transport does not contain any Transportables.");
        }
    }

    private void calculateUnload(Transportable loadable) {
        transportedWeight = transportedWeight - loadable.getWeight();
        currentTransportedVehicles = currentTransportedVehicles - 1;
        loadable.setBeingTransportState(false);
        onTransport.remove(loadable);
    }

    double speedFactor() {
        if (allowedToMove) {
            return enginePower * 0.3 * ((double)(maxWeight/(weight + transportedWeight)));
        } else {
            return 0; // Makes it so that incrementSpeed returns 0
        }
    }

}

