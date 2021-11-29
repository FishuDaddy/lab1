import org.testng.annotations.Test;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest {

    @Test
    public void doorsShouldBeFour() {
        Scania scania = new Scania();
        assertEquals(2, scania.nrDoors);
    }
    @Test
    public void enginePowerShouldBe200() {
        Scania scania = new Scania();
        assertEquals(200, scania.engine.enginePower, 0.01);
    }
    @Test
    public void colorShouldBeBlack() {
        Scania scania = new Scania();
        assertEquals(Color.black, scania.color);
    }
    @Test
    public void ShouldBeStationary() {
        Scania scania = new Scania();
        assertFalse(scania.isStationary());
    }
}
