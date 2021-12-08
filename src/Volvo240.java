import java.awt.*;

class Volvo240 extends TransportableCar {

    private final static double trimFactor = 1.25;
    
    public Volvo240(){
        commonAssembler(4, 100, Color.black, "Volvo240", 1400);
        pos = new Position(0, 0, 45);
     }
    double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}