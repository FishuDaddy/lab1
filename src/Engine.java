public class Engine {
    protected boolean engineOn;
    protected int enginePower;

    public Engine(int enginePower) {
        engineOn = false;
        this.enginePower = enginePower;
    }
    public double getEnginePower() {
        return enginePower;
    }
    protected void toggleEngine(){
        engineOn = !engineOn;
    }
}
