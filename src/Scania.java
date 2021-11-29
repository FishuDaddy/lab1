import java.awt.*;

/**
 * Represents a Scania Vehicle with a liftable platform
 */

public class Scania extends Car {

    CargoRamp ramp;

    /**
     * Constructor for creating an object of a Scania type vehicle with a ramp
     */
    public Scania() {
        assembler(2, 200, Color.black, "Scania");
        ramp = new CargoRamp(5, 70); // Delegation of Cargo ramp
    }

    @Override
    public double speedFactor() {
        if (!isStationary() && !ramp.platformRaised) {
            return engine.enginePower * 0.01;
        } else {
            return 0; // Vehicle can't move while platform is raised
        }
    }

    public boolean isStationary() {
        return currentSpeed == 0; // checks if currentSpeed is 0
    }

    public void togglePlatform() throws Exception {
        if (isStationary()) {
            ramp.platformRaised = !ramp.platformRaised;
        } else {
            throw new Exception("Vehicle need to be stationary to use the platform");
        }
    }

}
