import java.awt.*;

public class Scania extends TransportVehicle {

    private boolean platformRaised;
    private int platformAngle;

    /**
     * Assembles the Scania class and makes sure the variables are set correctly
     */
    public Scania() {
        commonAssembler(2, 200, Color.black, "Scania", 19000);
        transportAssembler(40000, 10, 10, true);
        modelSpecificConstructor();
    }
    public boolean isPlatformRaised() {
        return (platformRaised);
    }

    private void modelSpecificConstructor() {
        platformRaised = false;
        platformAngle = 0;
    }

    public int getPlatformAngle() {
        return platformAngle;
    }
    public void togglePlatform() throws Exception {
        if (isStationary()) {
            platformRaised = !platformRaised;
            allowedToMove = !platformRaised;
        } else {
            throw new Exception("Vehicle need to be stationary to use the platform");
        }
    }
    public void raisePlatform() throws Exception {
        if (platformRaised && platformAngle < 70) {
            platformAngle += 5;
        } else if (platformAngle >= 70) {
            throw new Exception("Platform cannot be raised above 70 degrees");
        } else {
            throw new Exception("Platform needs to be raised before use");
        }
    }
    public void lowerPlatform() throws Exception {
        if (platformRaised && platformAngle > 0) {
            platformAngle -= 5;
        } else if (platformAngle <= 0) {
            throw new Exception("Platform cannot be lowered below 0 degrees");
        } else {
            throw new Exception("Platform needs to be raised before use");
        }
    }

    @Override
    protected boolean modelSpecificConditionsMet(Transportable target) {
        if (!platformRaised) {
            return !(target instanceof MotorVehicle);
        } else return false;
    }
}
