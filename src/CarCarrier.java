import java.awt.*;

public class CarCarrier extends Car {

    protected HaulerRamp carrierRamp;

    public CarCarrier(int carLimit) {
        assembler(2, 250, Color.cyan, "KingDaddyHauler");
        this.carrierRamp = new HaulerRamp(4);
    }

    @Override
    public double speedFactor() {
        return engine.enginePower * 0.01;
    }

    public boolean isStationary() {
        return currentSpeed == 0; // checks if currentSpeed is 0
    }

    public void togglePlatform() throws Exception {
        if (isStationary()) {
            carrierRamp.platformRaised = !carrierRamp.platformRaised;
        } else {
            throw new Exception("Vehicle need to be stationary to use the platform");
        }
    }
    public void toggleRamp() throws Exception {
        if (carrierRamp.platformRaised && !carrierRamp.rampDown) {
            carrierRamp.rampDown = true;
        } else if (carrierRamp.platformRaised) {
            carrierRamp.rampDown = false;
        } else {
            throw new Exception("Platform needs to be raised before use");
        }
    }
}
