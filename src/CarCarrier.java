import java.awt.*;
import java.util.ArrayList;

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
}
