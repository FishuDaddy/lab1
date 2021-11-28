import java.awt.*;

/**
 * Represents a car of the model Saab95
 */

class Saab95 extends Car {
    
    protected boolean turboOn;

    public Saab95() {
        assembler(2, 125, Color.red, "Saab95");
        turboOn = false;
        dir = 45;
    }

    public void setTurboOn(){
	    turboOn = true;
    }
    public void setTurboOff(){
	    turboOn = false;
    }
    
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }


}
