import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;


public class EngineTest{

    Engine engine = new Engine(100);

    @Test
    public void engineShouldBeOff(){
        assertFalse(engine.engineOn);
    }
    @Test
    public void getEnginePower(){
        assertEquals(100, engine.enginePower);
    }
    @Test
    public void toggleEngine(){
        engine.engineToggle();
        assertTrue(engine.engineOn);
    }

}
