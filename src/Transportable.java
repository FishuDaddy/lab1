public interface Transportable extends CoordinateDependent {
    int getWeight();
    boolean isBeingTransported();
    boolean canBeLoaded();
    void setBeingTransportState(boolean state);
}
