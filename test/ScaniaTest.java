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
    public void engineShouldTurnOn() {
        Scania scania = new Scania();
        scania.toggleEngine();
        assertEquals(0.1, scania.currentSpeed);
    }
    @Test
    public void ShouldBeStationary() {
        Scania scania = new Scania();
        assertTrue(scania.isStationary());
    }
    @Test
    public void modelName() {
        Scania scania = new Scania();
        assertEquals("Scania", scania.modelName);
    }
    @Test
    public void scaniaWeight() {
        Scania scania = new Scania();
        assertEquals(3000, scania.weight);
    }
    @Test
    public void speedFactorShouldBeZero() {
        Scania scania = new Scania();
        assertEquals(0, scania.speedFactor());
    }
    @Test
    public void speedFactorShouldBeZeroIfRampIsInUse() throws Exception {
        Scania scania = new Scania();
        scania.togglePlatform();
        assertEquals(0, scania.speedFactor());
    }
    @Test
    public void speedFactorShouldBeAboveZero() {
        Scania scania = new Scania();
        scania.toggleEngine();
        scania.gas(1);
        assertTrue(scania.speedFactor() > 0);
    }
    @Test
    public void platformShouldRaise() throws Exception {
        Scania scania = new Scania();
        scania.togglePlatform();
        assertTrue(scania.cargoRamp.platformUse);
    }
    @Test
    public void ExceptionShouldBeThrown() {
        Exception thrown = assertThrows(Exception.class, () -> {
            Scania scania = new Scania();
            scania.toggleEngine();
            scania.gas(1);
            scania.togglePlatform();
        });
}   }
