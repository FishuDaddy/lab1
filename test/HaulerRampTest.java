import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import javax.sql.rowset.CachedRowSet;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;

public class HaulerRampTest {

    @Test
    public void haulSize() {
        HaulerRamp haulerRamp = new HaulerRamp(2);
        assertEquals(0, haulerRamp.getHaulSize());
    }

    @Test
    public void AbleToLowerRamp() throws Exception {
        HaulerRamp haulerRamp = new HaulerRamp(2);
        haulerRamp.switchState();
        haulerRamp.lowerRamp();
        assertTrue(haulerRamp.rampDown);
    }

    @Test
    public void AbleToRaiseRamp() throws Exception {
        HaulerRamp haulerRamp = new HaulerRamp(2);
        haulerRamp.switchState();
        haulerRamp.lowerRamp();
        haulerRamp.raiseRamp();
        assertFalse(haulerRamp.rampDown);
    }

    @Test
    public void FirstExceptionRaise() {
        Exception thrown = assertThrows(Exception.class, () -> {
            HaulerRamp haulerRamp = new HaulerRamp(2);
            haulerRamp.raiseRamp();
        });
    }
    @Test
    public void FirstExceptionLower() {
        Exception thrown = assertThrows(Exception.class, () -> {
            HaulerRamp haulerRamp = new HaulerRamp(2);
            haulerRamp.lowerRamp();
        });
    }
    @Test
    public void SecondExceptionRaise() {
        Exception thrown = assertThrows(Exception.class, () -> {
            HaulerRamp haulerRamp = new HaulerRamp(2);
            haulerRamp.switchState();
            haulerRamp.raiseRamp();
        });
    }
    @Test
    public void SecondExceptionLower() {
        Exception thrown = assertThrows(Exception.class, () -> {
            HaulerRamp haulerRamp = new HaulerRamp(2);
            haulerRamp.switchState();
            haulerRamp.lowerRamp();
            haulerRamp.lowerRamp();
        });
    }
    @Test
    public void notFull() {
        HaulerRamp haulerRamp = new HaulerRamp(2);
        assertTrue(haulerRamp.notFull());
    }
}
