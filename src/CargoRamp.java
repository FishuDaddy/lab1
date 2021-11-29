public class CargoRamp extends Ramp {

    protected int angleChange;
    protected int angleLimit;
    protected int platformAngle;

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
    public void raisePlatform() throws Exception{
        if (platformRaised && platformAngle < angleLimit) {
            platformAngle += angleChange;
        } else if (platformAngle >= angleLimit) {
            throw new Exception("Platform cannot be raised above 70 degrees");
        } else {
            throw new Exception("Platform needs to be raised before use");
        }
    }

    @Override
    public void lowerPlatform() throws Exception {
        if (platformRaised && platformAngle > 0) {
            platformAngle -= angleChange;
        } else if (platformAngle <= 0) {
            throw new Exception("Platform cannot be lowered below 0 degrees");
        } else {
            throw new Exception("Platform needs to be raised before use");
        }

    }
}
