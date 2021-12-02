/**
 * Represents a Cargo ramp which can be raised and lowered to haul cargo
 */
public class CargoRamp extends Ramp {

    protected int angleChange;
    protected int angleLimit;
    protected int platformAngle;

    /**
     * Creates a cargo ramp
     *
     */
    public CargoRamp() {
        ramp();
        this.angleChange = 5;
        this.angleLimit = 70;
        this.platformAngle = 0;
    }

    public int getPlatformAngle() {
        return platformAngle;
    }
    protected void setPlatformAngle(int newAngle) {
        platformAngle = newAngle;
    }

    @Override
    public void raiseRamp() throws Exception {
        if (platformUse) {
            if (getPlatformAngle() < angleLimit) {
                platformAngle += angleChange;
            } else {
                throw new Exception("Platform cannot be raised above 70 degrees");
            }
        } else {
            throw new Exception("Platform needs to be activated before use");
        }
    }

    @Override
    public void lowerRamp() throws Exception {
        if (platformUse) {
            if (getPlatformAngle() > 0) {
                platformAngle -= angleChange;
            } else {
                throw new Exception("Platform cannot be lowered below 0 degrees");
            }
        } else {
            throw new Exception("Platform needs to be activated before use");
        }
    }



}
