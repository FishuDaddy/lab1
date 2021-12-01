abstract class MediumSizedCar extends MotorVehicle implements Transportable {
    public int getWeight() {
        return weight;
    }

    public void load(TransportVehicle transport) {
        if (transport.canBeLoaded(this)) {
            transport.load(this);
        }
    }
    public void unload(TransportVehicle transport) {

    }
}
