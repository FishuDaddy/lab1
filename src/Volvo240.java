import java.awt.*;

/**
 * Represents a car of the model Volvo240
 */

class Volvo240 extends Car {

    private final static double trimFactor = 1.25;

    /**
     * Constructor for creating a Volvo240 object
     */
    public Volvo240(){
        assembler(4, 100, Color.black, "Volvo240");
        dir = 45;
    }
    public double speedFactor(){
        return engine.enginePower * 0.01 * trimFactor;
    }
}