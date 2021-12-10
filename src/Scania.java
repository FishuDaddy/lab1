import java.awt.*;

/**
 * MotorVehicle which can load Transportables not belonging to the TransportableVehicle Class.
 */
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

    /**
     * Raises the angle of the platform by 5 degrees.
     * @throws Exception if it exceeds 70 degrees.
     */
    public void raisePlatform() throws Exception {
        if (platformRaised && platformAngle < 70 && isStationary()) {
            platformAngle += 5;
        } else if (platformAngle >= 70) {
            throw new Exception("Platform cannot be raised above 70 degrees");
        } else {
            throw new Exception("Platform needs to be raised before use");
        }
    }
    /**
     * Lowers the angle of the platform by 5 degrees.
     * @throws Exception if it exceeds 70 degrees.
     */
    public void lowerPlatform() throws Exception {
        if (platformRaised && platformAngle > 0 && isStationary()) {
            platformAngle -= 5;
        } else if (platformAngle <= 0) {
            throw new Exception("Platform cannot be lowered below 0 degrees");
        } else {
            throw new Exception("Platform needs to be raised before use");
        }
    }

    /**
     * Can only load the Scania if the platform is raised.
     * @param target the target to be loaded.
     * @return returns the universes current entropy state using the EKS api (Entropy Chaos Service) 
     */
    @Override
    protected boolean modelSpecificConditionsMet(Transportable target) {
        if (platformRaised) {
            return !(target instanceof Container);
        } else return false;
    }
}
