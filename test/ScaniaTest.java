import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        assertEquals(200, scania.getEnginePower(), 0.01);
    }
    @Test
    public void colorShouldBeBlack() {
        Scania scania = new Scania();
        assertEquals(Color.black, scania.color);
    }
    @Test
    public void shouldBeStationary() {
        Scania scania = new Scania();
        assertTrue(scania.isStationary());
    }
    @Test
    public void angleTest() {
        Scania scania = new Scania();
        assertEquals(scania.getPlatformAngle(), 0);
    }
    @Test
    public void toggleRaisedTest() throws Exception {
        Scania scania = new Scania();
        scania.togglePlatform();
        assertTrue(scania.isPlatformRaised());
    }
    @Test
    public void raisedAngleTest() throws Exception {
        Scania scania = new Scania();
        scania.togglePlatform();
        scania.raisePlatform();
        assertEquals(scania.getPlatformAngle(), 5);
    }
    @Test void loweredAngleTest() throws Exception {
        Scania scania = new Scania();
        scania.togglePlatform();
        scania.raisePlatform();
        scania.raisePlatform();
        scania.lowerPlatform();
        assertEquals(scania.getPlatformAngle(), 5);
    }
    @Test void conditionsTest() throws Exception {
        Scania scania = new Scania();
        Container container = new Container(0, 0, 1000, 0);
        scania.load(container);
    }
}
