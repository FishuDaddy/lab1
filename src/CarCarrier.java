import java.awt.*;

public class CarCarrier extends CarTransport {

    protected HaulerRamp carrierRamp;

    public CarCarrier(int carLimit) {
        commonAssembler(2, 250, Color.cyan, "KingDaddyHauler", 4000);
        transportAssembler(8000);
        this.carrierRamp = new HaulerRamp(carLimit);
    }

    @Override
    public double speedFactor() {
        return engine.enginePower * 0.01;
    }

    public void togglePlatform() throws Exception {
        if (isStationary()) {
            carrierRamp.switchState();
        } else {
            throw new Exception("Vehicle need to be stationary to use the platform");
        }
    }

    public void loadVehicle(Object vehicle) {
        if (carrierRamp.rampDown && carrierRamp.onTransport.size() < carrierRamp.carLimit && vehicle != this) { // TODO validate distance
            carrierRamp.onTransport.push(vehicle);
        }
    }
    public void unloadVehicle() {
        if (carrierRamp.rampDown) {
            carrierRamp.onTransport.pop();
        }
    }
    @Override
    public void move(){
        this.x += getCurrentSpeed() * Math.cos(Math.toRadians(this.dir));
        this.y += getCurrentSpeed() * Math.sin(Math.toRadians(this.dir));
    }
}
