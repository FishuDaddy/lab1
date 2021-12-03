import java.awt.*;
import java.security.InvalidParameterException;

/**
 * Abstract class with common methods and attributes for implementation of a Car-like object
 */

abstract class MotorVehicle implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected int x;
    protected int y;
    protected int dir; // The Car's direction in Degrees, subject to tweaks
    protected Engine engine;
    protected int weight; // Weight in kg
    protected boolean isStationary;
    protected boolean canMove;

    /**
     * Constructor for the rudimentary parts of a car- like object
     * @param nrDoors The vehicle's number of doors
     * @param enginePower The vehicles' engine power
     * @param color the vehicles' color
     * @param modelName the vehicles' model name
     */
    protected void commonAssembler(int nrDoors, int enginePower, Color color, String modelName, int weight) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.modelName = modelName;
        this.engine = new Engine(enginePower);
        this.weight = weight;
        this.isStationary = true;
        this.dir = 0;
        this.canMove = true;
    }

    protected  int getNrDoors(){
        return nrDoors;
    }
    protected double getCurrentSpeed(){
        return currentSpeed;
    }
    protected Color getColor(){
        return color;
    }
    public void setColor(Color clr) {color = clr;}
    protected boolean isStationary() {
        return currentSpeed == 0; // checks if currentSpeed is 0
    }
    protected int getWeight() {
        return weight;
    }
    protected void toggleEngine(){
        engine.engineToggle();
        if (engine.engineOn){
            currentSpeed = 0.1;
        } else {
            currentSpeed = 0;
        }
    }

    abstract public double speedFactor(); // Instantiated objects have to implement as they differ

    private void incrementSpeed(double amount){
        if (engine.engineOn){
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, engine.enginePower);
        } else {
            currentSpeed = 0;
        }
    }

    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    /**
     * Method that increases the vehicle's current speed
     * @param amount a double from 0 to 1
     * @throws InvalidParameterException throws a custom exception depending on the error
     */
    public void gas(double amount) throws InvalidParameterException{
        if (amount <= 1 && amount >= 0 && canMove){
            incrementSpeed(amount);
        } else {
            throw new InvalidParameterException("Please input an amount in the interval [0,1]");
        }
    }

    /**
     * Method that decreases the vehicle's current speed
     * @param amount a double from 0 to 1
     * @throws InvalidParameterException throws a custom exception depending on the error
     */
    public void brake(double amount) throws InvalidParameterException {
        if (amount <= 1 && amount >= 0 && canMove) {
            decrementSpeed(amount);
        } else {
            throw new InvalidParameterException("Please input an amount in the interval [0,1]");
        }
    }

    protected void setX(int x) {
        this.x = x;
    }
    protected void setY(int y) {
        this.y = y;
    }
    protected void setDir(int dir) {
        this.dir = dir;
    }
    protected void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(){
        this.x += getCurrentSpeed() * Math.cos(Math.toRadians(this.dir));
        this.y += getCurrentSpeed() * Math.sin(Math.toRadians(this.dir));
    }

    @Override
    public void turnLeft(){
        this.dir += 45;
        this.dir %= 360;
    }
    @Override
    public void turnRight() {
        this.dir -= 45;
        this.dir %= 360;
    }
}
