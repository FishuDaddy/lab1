import java.awt.*;

/**
 * Represents a car of the model Volvo240
 */

class Volvo240 extends MediumCar {

    private final static double trimFactor = 1.25;

    /**
     * Constructor for creating a Volvo240 object
     */
    public Volvo240(){
        commonAssembler(4, 100, Color.black, "Volvo240", 2500);
        dir = 45;
    }
    public double speedFactor(){
        return engine.enginePower * 0.01 * trimFactor;
    }
}