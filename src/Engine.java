/**
 * Represents the engine of a car
 * Constructor for creating an engine with a set amount of engine power that is turned off
 */

public class Engine {
    protected boolean engineOn;
    protected int enginePower;

    /**
     * Constructor for an Engine object which is used to handle engine appropriate events
     * @param enginePower The engines maximum power output
     */
    public Engine(int enginePower) {
        engineOn = false;
        this.enginePower = enginePower;
    }
    public double getEnginePower() {
        return enginePower;
    }
    protected void engineToggle(){
        engineOn = !engineOn;
    }
}
