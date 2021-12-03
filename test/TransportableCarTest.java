import org.junit.jupiter.api.Test;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;

public class TransportableCarTest {

    @Test
    public void isBeingTransportedFalse() {
        Saab95 saab = new Saab95();
        assertFalse(saab.isBeingTransported());
    }
}
