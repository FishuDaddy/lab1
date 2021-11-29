import java.util.Stack;

/**
 * Represents a ramp which can be loaded with cars for transport
 */

public class HaulerRamp extends Ramp{

    protected boolean rampDown;
    protected int carLimit;
    protected Stack<Object> load;

    /**
     * Constructor for creating a hauler ramp with a set limit of cars allowed at a time
     * @param carLimit the maximum amount of cars/vehicles the ramp can hold
     */
    public HaulerRamp(int carLimit) {
        ramp();
        this.carLimit = carLimit; // The maximum amount of cars that can be loaded
        load = new Stack<>(); // Creates a stack datastructure for the loading- and unloading of cars
        rampDown = false; // Ramp starts off as up
    }


}
