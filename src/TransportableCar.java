abstract class TransportableCar extends MotorVehicle implements Transportable {
    private boolean beingTransported = false;
    public int getWeight() {
        return weight;
    }
    public boolean isBeingTransported() {
        return beingTransported;
    }
    public boolean canBeLoaded() {
        return (!beingTransported && isStationary());
    }
    public void setBeingTransportState(boolean state){
        this.beingTransported = state;
    }
}
