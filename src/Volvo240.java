import java.awt.*;

class Volvo240 extends Car {

    private final static double trimFactor = 1.25;
    
    public Volvo240(){
        assembler(4, 100, Color.black, "Volvo240");
        dir = 45;
    }
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}