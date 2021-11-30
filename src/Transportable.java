public interface Transportable {
    int getWeight();
    void load(CarTransport transport);
    void unload(CarTransport transport);
}
