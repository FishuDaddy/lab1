import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import javax.sql.rowset.CachedRowSet;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;

public class CargoRampTest {

    @Test
    public void getPlatformAngleZero() {
        CargoRamp cargoRamp = new CargoRamp();
        assertEquals(0, cargoRamp.getPlatformAngle());
    }
    @Test
    public void a() {
        CargoRamp cargoRamp = new CargoRamp();
        assertEquals(0, cargoRamp.getPlatformAngle());
    }
    @Test
    public void raisePlatformTrue() throws Exception {
        CargoRamp cargoRamp = new CargoRamp();
        cargoRamp.switchState();
        cargoRamp.raiseRamp();
        assertEquals(5, cargoRamp.getPlatformAngle());
    }

    @Test
    public void exceptionShouldBeThrown(){
        Exception thrown =  assertThrows(Exception.class, () -> {
            CargoRamp cargoRamp = new CargoRamp();
            cargoRamp.raiseRamp();
        });
    }
    @Test
    public void exceptionShouldBeThrownWhenRaising(){
        Exception thrown =  assertThrows(Exception.class, () -> {
            CargoRamp cargoRamp = new CargoRamp();
            cargoRamp.switchState();
            cargoRamp.setPlatformAngle(70);
            cargoRamp.raiseRamp();
        });
    }
    @Test
    public void lowerRampPlease() throws Exception {
        CargoRamp cargoRamp = new CargoRamp();
        cargoRamp.switchState();
        cargoRamp.raiseRamp();
        cargoRamp.raiseRamp();
        cargoRamp.lowerRamp();
        assertEquals(5, cargoRamp.getPlatformAngle());
    }
    @Test
    public void lowerPlatformShouldGiveException(){
        Exception thrown =  assertThrows(Exception.class, () -> {
            CargoRamp cargoRamp = new CargoRamp();
            cargoRamp.lowerRamp();
        });
    }
    @Test
    public void lowerPlatformShouldGiveExceptionZero(){
        Exception thrown =  assertThrows(Exception.class, () -> {
            CargoRamp cargoRamp = new CargoRamp();
            cargoRamp.switchState();
            cargoRamp.lowerRamp();
        });
    }
    @Test
    public void getAngle() throws Exception {
        CargoRamp cargoRamp = new CargoRamp();
        cargoRamp.switchState();
        cargoRamp.raiseRamp();
        assertEquals(5, cargoRamp.getPlatformAngle());
    }

}
