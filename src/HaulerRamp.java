import java.util.Stack;

/**
 * Represents a ramp which can be loaded with cars for transport
 */

public class HaulerRamp extends Ramp{

    protected boolean rampDown;
    protected int carLimit;
    protected Stack<MotorVehicle> onTransport;

    /**
     * Constructor for creating a hauler ramp with a set limit of cars allowed at a time
     * @param carLimit the maximum amount of cars/vehicles the ramp can hold
     */
    public HaulerRamp(int carLimit) {
        ramp();
        this.carLimit = carLimit; // The maximum amount of cars that can be loaded
        onTransport = new Stack<>(); // Creates a stack datastructure for the loading- and unloading of cars
        rampDown = false; // Ramp starts off as up
    }

    public String getCurrentLoad() {
        return "Current load >" + onTransport;
    }


    @Override
    public void raiseRamp() throws Exception {
        if (platformUse && rampDown) {
            rampDown = false;
        } else if (platformUse) {
            throw new Exception("Platform is already raised");
        } else {
            throw new Exception("Platform need to be in use before operation");
        }
    }

    @Override
    public void lowerRamp() throws Exception {
        if (platformUse && !rampDown) {
            rampDown = true;
        } else if (platformUse) {
            throw new Exception("Platform is already lowered");
        } else {
            throw new Exception("Platform need to be in use before operation");
        }
    }
    public boolean notFull() {
        return onTransport.size() < carLimit;
    }

    public int getSize() {
        return onTransport.size();
    }

}
