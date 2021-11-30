public interface Transportable extends CoordinateDependent {
    int getWeight();
    void load(CarTransport transport);
    void unload(CarTransport transport);

}
