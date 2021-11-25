import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class Volvo240test {
    public Volvo240 volvo = new Volvo240();
    @Test
    public void volvo_doors_should_be_four(){
        assertEquals(4, volvo.nrDoors);
    }
    @Test
    public void color_should_be_black() {
        assertEquals(Color.black, volvo.color);
    }
    @Test
    public void volvo_enginePower() {
        assertEquals(100, volvo.enginePower, 0.01);
    }

    @Test
    public void speedFactor(){
        Volvo240 volvo = new Volvo240();
        assertEquals(1.25, volvo.speedFactor(), 0.01);
    }
}
