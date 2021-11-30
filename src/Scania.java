import java.awt.*;
import java.util.ArrayList;

public class Scania extends CarTransport {

    protected boolean platformRaised;
    protected int platformAngle;

    /**
     * Assembles the Scania class and makes sure the variables are set correctly
     */
    public Scania() {
        commonAssembler(2, 200, Color.black, "Scania", 19000);
        transportAssembler(40000, 10, 10);
        platformRaised = false;
        platformAngle = 0;
    }
    public int getPlatformAngle() {
        return platformAngle;
    }
    public void toggleStationary() {
        isStationary = currentSpeed == 0; // checks if currentSpeed is 0
    }
    public void togglePlatform() throws Exception {
        if (isStationary) {
            platformRaised = !platformRaised;
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
}
