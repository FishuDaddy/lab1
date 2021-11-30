import org.testng.annotations.Test;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;


public class MotorVehicleTest {

    public Volvo240 volvo = new Volvo240();

    @Test
    public void volvo_doors_four(){
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    public void volvo_engine_100(){
        assertEquals(100, volvo.engine.getEnginePower(), 0.01);
    }

    @Test
    public void car_should_turn_45_degrees() {
        Volvo240 volvo = new Volvo240();
        volvo.dir = 315;
        volvo.turnLeft();
        assertEquals(0, volvo.dir);
    }
    @Test
    public void test_both_startEngine(){
        volvo.toggleEngine();
        assertEquals(0.1, volvo.getCurrentSpeed(), 0.01);
    }
    @Test
    public void get_color(){
        Volvo240 volvo1 = new Volvo240();
        volvo1.setColor(Color.yellow);
        assertEquals(Color.yellow, volvo1.getColor());
    }
    @Test
    public void shouldNotBeAbleToGasWithEngineOff(){
        Volvo240 volvo = new Volvo240(); // A car should not be able to accelerate without the engine being on
        volvo.gas(1);
        assertEquals(0, volvo.getCurrentSpeed(), 0.01);
    }
    @Test
    public void move_not_should_move_if_engineOff(){
        Volvo240 volvo = new Volvo240();
        volvo.dir = 45;
        volvo.move();
        assertEquals(0, volvo.x, 0.01);
    }
    @Test
    public void should_move_if_engineOn() throws InvalidParameterException {
        Volvo240 volvo = new Volvo240();
        volvo.dir = 90;
        volvo.y = 0;
        volvo.toggleEngine();
        volvo.gas(1);
        volvo.move();
        assertTrue(volvo.y > 0);
    }
    @Test
    public void togglingEngineOnOffShouldBeOff(){
        Volvo240 volvo = new Volvo240();
        volvo.toggleEngine(); // Turns the engine on
        volvo.toggleEngine(); // Should turn the engine back off
        assertFalse(volvo.engine.engineOn);
    }
    @Test
    public void brake_should_lower_currentSpeed() throws InvalidParameterException {
        Volvo240 volvo = new Volvo240();
        volvo.toggleEngine();
        volvo.gas(1);
        double v0 = volvo.getCurrentSpeed();
        volvo.brake(0.5);
        assertTrue(volvo.getCurrentSpeed() < v0);
    }
    @Test
    public void turn_right_should_45(){
        Volvo240 volvo = new Volvo240();
        volvo.dir = 90;
        volvo.turnRight();
        assertEquals(45, volvo.dir);
    }
    @Test
    public void exceptionShouldBeThrownGas(){
        InvalidParameterException thrown =  assertThrows(InvalidParameterException.class, () -> {
            Volvo240 volvo = new Volvo240();
            volvo.gas(10);
        });
    }
    @Test
    public void exceptionShouldBeThrownBrake(){
        InvalidParameterException thrown =  assertThrows(InvalidParameterException.class, () -> {
            Volvo240 volvo = new Volvo240();
            volvo.brake(10);
        });
    }


}
