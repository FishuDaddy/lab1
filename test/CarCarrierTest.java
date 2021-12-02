import org.testng.annotations.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class CarCarrierTest {

    @Test
    public void instanceAttributes() {
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(2, carrier.nrDoors);
    }

    @Test
    public void enginePower() {
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(250, carrier.engine.enginePower);
    }

    @Test
    public void color() {
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(Color.cyan, carrier.getColor());
    }

    @Test
    public void weight() {
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(4000, carrier.weight);
    }

    @Test
    public void maxHaulWeight() {
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(8000, carrier.maxWeight);
    }

    @Test
    public void speedFactor() {
        CarCarrier carrier = new CarCarrier(4);
        assertEquals(2.5, carrier.speedFactor(), 0.01);
    }

    @Test
    public void toggleWork() throws Exception {
        CarCarrier carrier = new CarCarrier(4);
        carrier.toggleRamp();
        assertTrue(carrier.carrierRamp.platformUse);
    }

    @Test
    public void ThrowExceptionTogglePlatform() {
        Exception thrown = assertThrows(Exception.class, () -> {
            CarCarrier carrier = new CarCarrier(4);
            carrier.toggleEngine();
            carrier.toggleRamp();
        });
    }
    @Test
    public void inRange() {
        CarCarrier carrier = new CarCarrier(4);
        Saab95 saab = new Saab95();
        carrier.setPos(0,0);
        saab.setPos(4,3);
        assertTrue(carrier.inRange(saab));
    }
    @Test
    public void notItself() {
        CarCarrier carrier = new CarCarrier(4);
        assertFalse(carrier.notItself(carrier));
    }
    @Test
    public void notTooHeavy() {
        CarCarrier carrier = new CarCarrier(4);
        Saab95 saab = new Saab95();
        assertTrue(carrier.notTooHeavy(saab));
    }
    @Test
    public void Load() throws Exception {
        CarCarrier carrier = new CarCarrier(4);
        Saab95 saab = new Saab95();
        carrier.toggleRamp();
        carrier.carrierRamp.lowerRamp();
        carrier.loadVehicle(saab);
        assertEquals(1, carrier.carrierRamp.getHaulSize());
    }
    @Test
    public void Unload() throws Exception {
        CarCarrier carrier = new CarCarrier(4);
        Saab95 saab = new Saab95();
        carrier.toggleRamp();
        carrier.carrierRamp.lowerRamp();
        carrier.loadVehicle(saab);
        carrier.unloadVehicle();
        assertEquals(0, carrier.carrierRamp.getHaulSize());
    }
    @Test
    public void unloadBehind() throws Exception {
        CarCarrier carrier = new CarCarrier(4);
        Saab95 saab = new Saab95();
        carrier.setPos(10, 10);
        saab.setPos(10,10);
        carrier.setDir(90);
        carrier.toggleRamp();
        carrier.carrierRamp.lowerRamp();
        carrier.loadVehicle(saab);
        carrier.unloadVehicle();
        assertEquals(30, saab.y);

        assertTrue(carrier.notTooHeavy(saab));
    }
    @Test
    public void CantUnload() {
        Exception thrown = assertThrows(Exception.class, () -> {
            CarCarrier carrier = new CarCarrier(4);
            carrier.toggleRamp();
            carrier.carrierRamp.lowerRamp();
            carrier.unloadVehicle();
        });
    }
    @Test
    public void CantUnload2() {
        Exception thrown = assertThrows(Exception.class, () -> {
            CarCarrier carrier = new CarCarrier(4);
            carrier.unloadVehicle();
        });
    }
    @Test
    public void Orientation() throws Exception {
        CarCarrier carrier = new CarCarrier(4);
        Saab95 saab = new Saab95();
        carrier.setPos(10, 10);
        saab.setPos(10,10);

        carrier.toggleRamp();
        carrier.carrierRamp.lowerRamp();
        carrier.loadVehicle(saab);

        carrier.toggleEngine();
        carrier.gas(1);
        carrier.setDir(90);
        carrier.turnLeft();
        carrier.move();
        assertEquals(135, saab.dir);
    }
    @Test
    public void yCord() throws Exception {
        CarCarrier carrier = new CarCarrier(4);
        Saab95 saab = new Saab95();
        carrier.setPos(10, 10);
        saab.setPos(10,10);

        carrier.toggleRamp();
        carrier.carrierRamp.lowerRamp();
        carrier.loadVehicle(saab);

        carrier.toggleEngine();
        carrier.gas(1);
        carrier.setDir(90);
        carrier.move();
        assertTrue(saab.y > 10);
    }
    @Test
    public void xCord() throws Exception {
        CarCarrier carrier = new CarCarrier(4);
        Saab95 saab = new Saab95();
        carrier.setPos(10, 10);
        saab.setPos(10,10);

        carrier.toggleRamp();
        carrier.carrierRamp.lowerRamp();
        carrier.loadVehicle(saab);

        carrier.toggleEngine();
        carrier.gas(1);
        carrier.setDir(0);
        carrier.move();
        assertTrue(saab.x > 10);
    }

}
