import org.testng.annotations.Test;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;


public class CarCarrierTest {

    @Test
    public void instanceAttributes() {
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(2, carrier.nrDoors);
    }
    @Test
    public void enginePower(){
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(250, carrier.engine.enginePower);
    }
    @Test
    public void color(){
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(Color.cyan, carrier.getColor());
    }
    @Test
    public void weight(){
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(4000, carrier.weight);
    }
    @Test
    public void maxHaulWeight(){
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(8000, carrier.maxWeight);
    }

}
