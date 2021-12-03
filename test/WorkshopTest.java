import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest {
    Workshop<Transportable> genericWorkshop;
    int x = 5;
    int y = 6;
    int threshold = 10;
    int capacity = 10;
    int maxWeight = 10000;

    @Test
    void getX() {
        genericWorkshop = new Workshop<Transportable>(x, y, threshold, capacity, maxWeight);
        assertEquals(genericWorkshop.getX(), x);
    }

    @Test
    void getY() {
        genericWorkshop = new Workshop<Transportable>(x, y, threshold, capacity, maxWeight);
        assertEquals(genericWorkshop.getY(), y);
    }

    @Test
    void setCoordinatesX() {
        genericWorkshop = new Workshop<Transportable>(x, y, threshold, capacity, maxWeight);
        assertEquals(genericWorkshop.getY(), y);
        genericWorkshop.setCoordinates(2*x, 2*y);
        assertEquals(2*x, genericWorkshop.getX());
    }

    @Test
    void setCoordinatesY() {
        genericWorkshop = new Workshop<Transportable>(x, y, threshold, capacity, maxWeight);
        assertEquals(genericWorkshop.getY(), y);
        genericWorkshop.setCoordinates(2*x, 2*y);
        assertEquals(2*y, genericWorkshop.getY());
    }

    @Test
    void load() throws Exception {
        genericWorkshop = new Workshop<Transportable>(x, y, threshold, capacity, maxWeight);
        Volvo240 Car1 = new Volvo240();
        Saab95 Car2 = new Saab95();
        Exception thrown = assertThrows(Exception.class, () -> {
            genericWorkshop.load(Car1);
            genericWorkshop.load(Car1);
        });
    }

    @Test
    void unload() throws Exception {
        genericWorkshop = new Workshop<Transportable>(x, y, threshold, capacity, maxWeight);
        Volvo240 Car1 = new Volvo240();
        Saab95 Car2 = new Saab95();
        genericWorkshop.load(Car1);
        genericWorkshop.unload(Car1);
        assertEquals(genericWorkshop.getInWorkshop(), 0);
    }
}