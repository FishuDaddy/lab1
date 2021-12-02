import org.testng.annotations.Test;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;


public class WorkshopTest {


    @Test
    public void checkLimit(){
        Workshop<MotorVehicle> workshop = new Workshop<>(8);
        assertEquals(8, workshop.storageLimit);
    }
    @Test
    public void checkStorageSize(){
        Workshop<MotorVehicle> workshop = new Workshop<>(8);
        assertEquals(0, workshop.getStorageSize());
    }
    @Test
    public void CheckSizeAfterLoad() throws Exception{
        Workshop<MotorVehicle> workshop = new Workshop<>(8);
        Saab95 saab = new Saab95();
        Volvo240 volvo = new Volvo240();

        workshop.loadVehicle(saab);
        workshop.loadVehicle(volvo);
        assertEquals(2, workshop.getStorageSize());
    }
    @Test
    public void ThrowExceptionLoad() {
        Exception thrown =  assertThrows(Exception.class, () -> {
            Workshop<MotorVehicle> workshop = new Workshop<>(1);
            Saab95 saab = new Saab95();
            Volvo240 volvo = new Volvo240();

            workshop.loadVehicle(saab);
            workshop.loadVehicle(volvo);
        });
    }
    @Test
    public void ThrowExceptionUnload() {
        Exception thrown =  assertThrows(Exception.class, () -> {
            Workshop<MotorVehicle> workshop = new Workshop<>(1);
            Saab95 saab = new Saab95();

            workshop.unloadVehicle(saab);
        });
    }
    @Test
    public void CheckSizeAfterUnload() throws Exception{
        Workshop<MotorVehicle> workshop = new Workshop<>(8);
        Saab95 saab = new Saab95();
        Volvo240 volvo = new Volvo240();

        workshop.loadVehicle(saab);
        workshop.loadVehicle(volvo);
        workshop.unloadVehicle(volvo);
        assertEquals(1, workshop.getStorageSize());
    }


}
