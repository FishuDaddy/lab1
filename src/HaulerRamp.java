import java.util.Stack;

/**
 * Represents a ramp which can be loaded with cars for transport
 */

public class HaulerRamp extends Ramp {

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

    public int getHaulSize() {
        return onTransport.size();
    }

    /**
     * Raises the ramp if the conditions are met, being if the platform is activated and if ramp is lowered
     * @throws Exception throws a custom error depending on what returned false
     */
    @Override
    public void raiseRamp() throws Exception {
        if (platformUse) {
            if (rampDown) {
                rampDown = false;
            } else {
                throw new Exception("Platform is already raised");
            }
        } else {
            throw new Exception("Platform need to be in use before operation");
        }
    }

    /**
     * Lowers the ramp if the conditions are met, being if the platform is activated and if ramp is raised
     * @throws Exception throws a custom error depending on what returned false
     */
    @Override
    public void lowerRamp() throws Exception {
        if (platformUse) {
            if (!rampDown) {
                rampDown = true;
            } else {
                throw new Exception("Platform is already lowered");
            }
        } else {
            throw new Exception("Platform need to be in use before operation");
        }
    }

    public boolean notFull() {
        return onTransport.size() < carLimit;
    }


}
