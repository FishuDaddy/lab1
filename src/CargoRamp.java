/**
 * Represents a Cargo ramp which can be raised and lowered to haul cargo
 */
public class CargoRamp extends Ramp {

    protected int angleChange;
    protected int angleLimit;
    protected int platformAngle;

    /**
     * Creates a cargo ramp with parameters for angleChange and the ramps angleLimit
     * @param angleChange The change in the ramps angle per operation of lowering or raising
     * @param angleLimit The limit of which the ramp can be raised
     */
    public CargoRamp(int angleChange, int angleLimit) {
        ramp();
        this.angleChange = angleChange;
        this.angleLimit = angleLimit;
        this.platformAngle = 0;
    }

    public int getPlatformAngle() {
        return platformAngle;
    }

    @Override
    public void raiseRamp() throws Exception {
        if (platformUse && platformAngle < angleLimit) {
            platformAngle += angleChange;
        } else if (platformAngle >= angleLimit) {
            throw new Exception("Platform cannot be raised above 70 degrees");
        } else {
            throw new Exception("Platform needs to be raised before use");
        }
    }
    @Override
    public void lowerRamp() throws Exception {
        if (platformUse && platformAngle > 0) {
            platformAngle -= angleChange;
        } else if (platformAngle <= 0) {
            throw new Exception("Platform cannot be lowered below 0 degrees");
        } else {
            throw new Exception("Platform needs to be raised before use");
        }
    }



}
