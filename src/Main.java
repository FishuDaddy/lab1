import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();
        Workshop<Volvo240> workshop = new Workshop<>(8);
        workshop.loadVehicle(volvo);

    }
}
