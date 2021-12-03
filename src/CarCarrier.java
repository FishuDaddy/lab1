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

    /**
     * Method for setting an unloaded's car position always being behind the carrier
     * @param unloadedCar The vehicle being unloaded
     */
    private void unloadBehind(MotorVehicle unloadedCar) { // Makes it so that the car is unloaded behind the carrier regardless of direction
        final int unloadOffset = 20; // subject to tweaks
        unloadedCar.x -= unloadOffset * Math.cos(Math.toRadians(this.dir));
        unloadedCar.y += unloadOffset * Math.sin(Math.toRadians(this.dir));
    }

    /**
     * Method for checking the conditions before a car is loaded onto a Hauler.
     * @param vehicle The vehicle being loaded
     * @return Returns true if all the conditions are met
     * @throws Exception Throws a custom exception depending on what returned false
     */
    private boolean loadConditionsMet(MotorVehicle vehicle) throws Exception {
        if (carrierRamp.rampDown) {
            if (inRange(vehicle)) {
                if (notItself(vehicle)) {
                    if (notTooHeavy(vehicle)) {
                        if (carrierRamp.notFull()) {
                            if (vehicle.canMove) {
                                return true;
                            } else {
                                throw new Exception("Vehicle is currently loaded");
                            }
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

    /**
     * A method that returns true if all of the conditions are met. This includes if the ramp is currently lowered, and if there is a vehicle on the ramp
     * @return Returns true if all the conditions are met
     * @throws Exception Throws a custom exception depending on what returned false
     */
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

    /**
     * A method for loading a vehicle onto the given ramp. First it checks if all the conditions are met for loading
     * then it pushed the vehicle onto a stack that simulates ramp storage structure, then it adds the vehicle's weight onto the total weight. When the vehicle
     * is in a loaded state it cannot move
     * @param vehicle The given vehicle being loaded
     * @throws Exception See "loadConditionsMet"
     */
    public void loadVehicle(MotorVehicle vehicle) throws Exception{
        if (loadConditionsMet(vehicle)) {
            carrierRamp.onTransport.push(vehicle);
            currentWeight += vehicle.getWeight();
            vehicle.canMove = false;
        }
    }

    /**
     * A method for unloading the vehicle. If the conditions are met for unloading (see "unloadConditionsMet") then it peeks onto the top of the stack,
     * it then stores that vehicle in a variable that is used to subtract it's weight and set it's "canMove" attribute to true. The vehicle is then popped of the stack
     * @throws Exception See "unloadConditionsMet"
     */
    public void unloadVehicle() throws Exception {
        if (unloadConditionsMet()) {
            MotorVehicle unloadedCar = carrierRamp.onTransport.peek();
            currentWeight -= unloadedCar.getWeight();
            unloadBehind(unloadedCar);
            carrierRamp.onTransport.pop();
            unloadedCar.canMove = true;
        }
    }

    /**
     * Overrides the move function to include the fact that every vehicle's position currently loaded has the same position as the carrier
     */
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
