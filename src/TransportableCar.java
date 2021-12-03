abstract class TransportableCar extends MotorVehicle implements Transportable {
    private boolean beingTransported = false;
    public int getWeight() {
        return weight;
    }

    /**
     * Returns whether the TransportableCar is currently on a transport or not.
     * @return true or false depending on state.
     */
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
