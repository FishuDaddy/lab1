import java.awt.*;

/**
 * Represents a Scania Vehicle with a liftable platform
 */

public class Scania extends CarTransport {

    CargoRamp cargoRamp;

    /**
     * Constructor for creating an object of a Scania type vehicle with a ramp
     */
    public Scania() {
        commonAssembler(2, 200, Color.black, "Scania", 3000);
        transportAssembler(500);
        cargoRamp = new CargoRamp(); // Delegation of Cargo ramp
    }

    @Override
    public double speedFactor() {
        if (!isStationary() && !cargoRamp.platformUse) {
            return engine.enginePower * 0.01;
        } else {
            return 0; // Vehicle can't move while platform is raised
        }
    }
    public void togglePlatform() throws Exception {
        if (isStationary()) {
            cargoRamp.switchState();
        } else {
            throw new Exception("Vehicle need to be stationary to use the platform");
        }
    }





}
