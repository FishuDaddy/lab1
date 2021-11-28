/**
 * Represents a Ramp which can be raised and lowered
 *
 */

public class Ramp implements Rampable {

    protected int platformAngle;
    protected boolean platformRaised;
    protected int angleChange;
    protected int angleLimit;


    /**
     * Constructor for creating an object of type Ramp
     * @param angleChange The incremental change of which the platform is raised- or lowered
     * @param angleLimit The maximum angle that the platform is allowed to be raised to
     */

    public Ramp(int angleChange, int angleLimit) {
        platformRaised = false;
        platformAngle = 0;
        this.angleChange = angleChange;
        this.angleLimit = angleLimit;
    }

    @Override
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