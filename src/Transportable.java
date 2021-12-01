public interface Transportable extends CoordinateDependent {
    int getWeight();
    void load(TransportVehicle transport);
    void unload(TransportVehicle transport);

}
