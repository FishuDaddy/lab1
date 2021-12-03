import org.junit.jupiter.api.Test;
import java.awt.*;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;


public class MANTest {

    @Test
    public void yo() {
        MAN man = new MAN();
        Saab95 saab = new Saab95();
        man.toggleRamp();
        assertTrue(man.modelSpecificConditionsMet(saab));
    }
}
