import java.util.ArrayList;

public class HaulerRamp extends Ramp{

    protected int carLimit;
    protected ArrayList<Object> load;

    public HaulerRamp(int carLimit) {
        ramp();
        this.carLimit = carLimit; // The maximum amount of cars that can be loaded
        this.load = new ArrayList<>(); // Creates an array (with the size carLimit) that will store the loaded cars information
    }
    @Override
    public void raisePlatform() throws Exception {
        if (platformRaised) {
            throw new Exception("Ramp already raised up");
        } else{
            platformRaised = true; // Raises the ramp up so that cars can't be loaded
        }
    }

    @Override
    public void lowerPlatform() throws Exception {
        if (!platformRaised) {
            throw new Exception("Ramp already lowered");
        } else {
            platformRaised = false; // Raises the platform down so that cars can be loaded
        }
    }
}
