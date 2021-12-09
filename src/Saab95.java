import java.awt.*;

class Saab95 extends TransportableCar {
    protected boolean turboOn;

    public Saab95() {
        commonAssembler(2, 125, Color.red, "Saab95", 1610);
        turboOn = false;
    }
    public void setTurboOn(){
	    turboOn = true;
    }
    public void setTurboOff(){
	    turboOn = false;
    }
    
    double speedFactor() {
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }


}
