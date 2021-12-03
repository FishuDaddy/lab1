/**
 * Defines an interface which can be implemented onto Objects to allow them to be transported.
 */
public interface Transportable extends DirectionAndCoordinateDependent {
    int getWeight();
    boolean isBeingTransported();
    boolean canBeLoaded();
    void setBeingTransportState(boolean state);
}
