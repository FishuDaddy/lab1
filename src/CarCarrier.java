import java.awt.*;

/**
 * Class representing a Truck with ramp able to haul vehicles
 */
public class CarCarrier extends CarTransport{

    protected HaulerRamp carrierRamp;

    /**
     * Creates the Car transport with a rudimentary parts and transport ramp
     * @param carLimit The maximum amount of cars the transport ramp can hold
     */
    public CarCarrier(int carLimit) {
        commonAssembler(2, 250, Color.cyan, "KingDaddyHauler", 4000);
        transportAssembler(8000);
        this.carrierRamp = new HaulerRamp(carLimit);
    }

    @Override
    public double speedFactor() {
        return engine.enginePower * 0.01;
    }

    public void toggleRamp() throws Exception {
        if (isStationary()) {
            carrierRamp.switchState();
        } else {
            throw new Exception("Vehicle need to be stationary to use the platform");
        }
    }
    protected boolean inRange(MotorVehicle vehicle) {
        return (Math.hypot(vehicle.x-this.x, vehicle.y-this.y) < 50);
    }
    protected boolean notItself(MotorVehicle vehicle) {
        return vehicle != this;
    }
    protected boolean notTooHeavy(MotorVehicle vehicle) {
        return (currentWeight + vehicle.weight) <= maxWeight;
    }

    private void unloadBehind(MotorVehicle unloadedCar) { // Makes it so that the car is unloaded behind the carrier regardless of direction
        final int unloadOffset = 20; // subject to tweaks
        unloadedCar.x -= unloadOffset * Math.cos(Math.toRadians(this.dir));
        unloadedCar.y += unloadOffset * Math.sin(Math.toRadians(this.dir));
    }
    private boolean loadConditionsMet(MotorVehicle vehicle) throws Exception {
        if (carrierRamp.rampDown) {
            if (inRange(vehicle)) {
                if (notItself(vehicle)) {
                    if (notTooHeavy(vehicle)) {
                        if (carrierRamp.notFull()) {
                            return true;
                        } else {
                            throw new Exception("Ramp is full");
                        }
                    } else {
                        throw new Exception("Vehicle's weight exceeds the allowed amount");
                    }
                } else {
                    throw new Exception("Can't load itself");
                }
            } else {
                throw new Exception("Vehicle not in range");
            }
        } else {
            throw new Exception("Ramp isn't lowered");
        }
    }
    private boolean unloadConditionsMet() throws Exception {
        if (carrierRamp.rampDown) {
            if (carrierRamp.getHaulSize() > 0) {
                return true;
            } else {
                throw new Exception("No vehicles on the transport");
            }
        } else {
            throw new Exception("Ramp isn't lowered");
        }
    }


    public void loadVehicle(MotorVehicle vehicle) throws Exception{
        if (loadConditionsMet(vehicle)) {
            carrierRamp.onTransport.push(vehicle);
            currentWeight += vehicle.getWeight();
        }
    }


    public void unloadVehicle() throws Exception {
        if (unloadConditionsMet()) {
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
            vehicle.setX(this.x);
            vehicle.setY(this.y);
            vehicle.setDir(this.dir);
        }
    }
}
