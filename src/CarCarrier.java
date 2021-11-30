import java.awt.*;
import java.util.Iterator;

public class CarCarrier extends CarTransport {

    protected HaulerRamp carrierRamp;
    private int unloadOffset = 20;

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
    private boolean inRange(MotorVehicle vehicle) {
        return (Math.hypot(vehicle.x-this.x, vehicle.y-this.y) < 20);
    }
    private boolean notItself(MotorVehicle vehicle) {
        return vehicle != this;
    }
    private boolean notTooHeavy(MotorVehicle vehicle) {
        return currentWeight + vehicle.weight <= maxWeight;
    }
    private void unloadBehind(MotorVehicle unloadedCar) { // Makes it so that the car is unloaded behind the carrier regardless of direction
        unloadedCar.x -= unloadOffset * Math.cos(Math.toRadians(this.dir));
        unloadedCar.y += unloadOffset * Math.sin(Math.toRadians(this.dir));
    }
    public void loadVehicle(MotorVehicle vehicle) {
        if (carrierRamp.rampDown && carrierRamp.notFull() && notTooHeavy(vehicle) && notItself(vehicle) && inRange(vehicle)) {
            carrierRamp.onTransport.push(vehicle);
            currentWeight += vehicle.getWeight();
        }
    }
    public void unloadVehicle() {
        if (carrierRamp.rampDown) {
            MotorVehicle unloadedCar = carrierRamp.onTransport.peek();
            currentWeight -= unloadedCar.getWeight();
            unloadBehind(unloadedCar);
            carrierRamp.onTransport.pop();

        }
    }
    @Override
    public void move(){
        this.x += getCurrentSpeed() * Math.cos(Math.toRadians(this.dir));
        this.y += getCurrentSpeed() * Math.sin(Math.toRadians(this.dir));
        for (MotorVehicle vehicle: carrierRamp.onTransport) {
            vehicle.x = this.x;
            vehicle.y = this.y;
            vehicle.dir = this.dir;
        }
    }
}
