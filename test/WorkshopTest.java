import org.testng.annotations.Test;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;


public class WorkshopTest {

    Saab95 saab = new Saab95();
    Volvo240 volvo = new Volvo240();

    @Test
    public void StorageShouldBe8(){
        Workshop workshop = new Workshop(8, null);
        assertEquals(8, workshop.storageLimit);
    }
    @Test
    public void checkCarTypeSaab(){
        Workshop workshop = new Workshop(8, "Saab95");
        assertEquals("class Saab95", workshop.carType);
    }
    @Test
    public void checkCarTypeVolvo(){
        Workshop workshop = new Workshop(8, "Volvo240");
        assertEquals("class Volvo240", workshop.carType);
    }
    @Test
    public void checkCarTypeScania(){
        Workshop workshop = new Workshop(8, "Scania");
        assertEquals("class Scania", workshop.carType);
    }
    @Test
    public void checkCarTypeCarCarrier(){
        Workshop workshop = new Workshop(8, "CarCarrier");
        assertEquals("class CarCarrier", workshop.carType);
    }
    @Test
    public void CheckStorageSize(){
        Workshop workshop = new Workshop(8, null);
        assertEquals(0, workshop.storage.size());
    }
    @Test
    public void CheckStorageSizeShouldBeOne() throws Exception {
        Workshop workshop = new Workshop(8, null);
        workshop.loadVehicle(saab);
        assertEquals(1, workshop.storage.size());
    }

}
