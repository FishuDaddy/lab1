import java.awt.*;

/**
 * Represents a Scania Vehicle with a liftable platform
 */

public class Scania extends Car{

    protected boolean isStationary;

    Ramp ramp = new Ramp(5, 70); // Delegation of object Ramp

    public Scania() {
        assembler(2, 200, Color.black, "Scania");
        isStationary = false;
    }

    @Override
    public double speedFactor() {
        if (!isStationary && !ramp.platformRaised) {
            return enginePower * 0.01;
        } else {
            return 0; // Vehicle can't move while platform is raised
        }
    }

    public int getPlatformAngle() {
        return ramp.getPlatformAngle();
    }

    public boolean getState() {
        return isStationary;
    }

    public boolean toggleStationary() {
        return isStationary = currentSpeed == 0; // checks if currentSpeed is 0
    }

    public void togglePlatform() throws Exception {
        if (isStationary) {
            ramp.platformRaised = !ramp.platformRaised;
        } else {
            throw new Exception("Vehicle need to be stationary to use the platform");
        }
    }

}
