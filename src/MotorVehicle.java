import java.awt.*;
import java.security.InvalidParameterException;

abstract class MotorVehicle implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected int weight;
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double x;
    protected double y;
    protected double dir; // The Car's direction in Degrees, subject to tweaks
    protected boolean engineOn;
    protected boolean allowedToMove;

    /**
     * Constructor for rudimentary parts of a motor vehicle
     * @param nrDoors The number of doors the vehicle has
     * @param enginePower The maximum engine power the vehicle has
     * @param color the color of the vehicle
     * @param modelName the vehicles model name
     * @param weight the vehicles weight
     */
    protected void commonAssembler(int nrDoors, int enginePower, Color color, String modelName, int weight) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.weight = weight;
        engineOn = false;
        allowedToMove = true;
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    protected void setColor(Color clr) {color = clr;}
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setCoordinates(double x, double y) {
        setX(x);
        setY(y);
    }
    public void setDirection(double direction) {
        this.dir = direction;
    }
    public double getDirection(){
        return dir;
    }

    public boolean isStationary() {
        return currentSpeed == 0;
    }

    protected void toggleEngine(){
        if (!engineOn){
            engineOn = true;
            currentSpeed = 0.1;
        } else {
            engineOn = false;
            currentSpeed = 0;
        }
    }

    abstract double speedFactor(); // Instantiated objects have to implement as they differ

    private void incrementSpeed(double amount){
        if (engineOn){
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        }
        else{
            currentSpeed = 0;
        }
    }
    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    /**
     * Method that increases the vehicles current speed by an amount
     * @param amount The amount of which the speed should increase given between 0, and 1
     * @throws Exception Throws a custom exception depending on error
     */
    public void gas(double amount) throws Exception {
        if (allowedToMove) {
            if (amount <= 1 && amount >= 0) {
                incrementSpeed(amount);
            } else {
                throw new InvalidParameterException("Please input an amount in the interval [0,1]");
            }
        } else {
            throw new Exception("MotorVehicle not allowed to move on it's own at the moment.");
        }
    }
    /**
     * Method that decreases the vehicles current speed by an amount
     * @param amount The amount of which the speed should decrease given between 0, and 1
     * @throws InvalidParameterException Throws a custom exception depending on error
     */
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
