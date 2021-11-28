import java.awt.*;
import java.security.InvalidParameterException;

/**
 * Abstract class with common methods and attributes for implementation of a Car-like object
 */

abstract class Car implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double x;
    protected double y;
    protected int dir; // The Car's direction in Degrees, subject to tweaks
    protected Engine engine;

    protected void assembler(int nrDoors, int enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.modelName = modelName;
        Engine engine = new Engine(enginePower);
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    protected void setColor(Color clr) {color = clr;}

    protected void toggleEngine(){
        engine.toggleEngine();
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
        }
        else{
            currentSpeed = 0;
        }
    }
    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
    public void gas(double amount) throws InvalidParameterException{
        if (amount <= 1 && amount >= 0){
            incrementSpeed(amount);
        } else {
            throw new InvalidParameterException("Please input an amount in the interval [0,1]");
        }
    }
    public void brake(double amount) throws InvalidParameterException {
        if (amount <= 1 && amount >= 0) {
            decrementSpeed(amount);
        } else {
            throw new InvalidParameterException("Please input an amount in the interval [0,1]");
        }
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
