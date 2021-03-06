import java.awt.*;

/**
 * Represents a car of the model Saab95
 */

class Saab95 extends MediumCar {
    
    protected boolean turboOn;

    /**
     * Constructor for creating a Saab95 object
     */
    public Saab95() {
        commonAssembler(2, 125, Color.red, "Saab95", 2000);
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
        return engine.enginePower * 0.01 * turbo;
    }


}
