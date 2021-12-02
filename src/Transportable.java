public interface Transportable extends DirectionAndCoordinateDependent {
    int getWeight();
    boolean isBeingTransported();
    boolean canBeLoaded();
    void setBeingTransportState(boolean state);
}
