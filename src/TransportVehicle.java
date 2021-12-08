import java.util.ArrayList;

abstract class TransportVehicle extends MotorVehicle {
    protected int transportedWeight;
    protected int maxWeight;
    private Loadable loadable;

    protected void transportAssembler(int maxWeight, int capacity, int loadThreshold, boolean allowedToMove) {
        loadable = new Loadable(capacity, loadThreshold, maxWeight-weight, true);
        this.maxWeight = maxWeight;
        this.allowedToMove = allowedToMove;
    }

    /**
     * Moves the Transporter as well as all the Transportables on-board.
     */
    @Override
    public void move() throws Exception {
        if (allowedToMove) {
            this.setX(this.getX() + getCurrentSpeed() * Math.cos(Math.toRadians(this.getDirection())));
            this.setY(this.getY() + getCurrentSpeed() * Math.sin(Math.toRadians(this.getDirection())));
            for (Transportable transported : loadable.onTransport) {
                transported.setCoordinates(this.getX(), this.getY());
            }
        } else throw new Exception("Car cannot move at the time");
    }

    /**
     * Turns the Transporter left as well as all the Transportables on-board.
     */
    @Override
    public void turnLeft(){
        this.incDirection(45);
        for (Transportable transported : loadable.onTransport) {
            transported.setDirection(this.getDirection());
        }
    }
    /**
     * Turns the Transporter right as well as all the Transportables on-board.
     */
    @Override
    public void turnRight() {
        this.incDirection(-45);
        for (Transportable transported : loadable.onTransport) {
            transported.setDirection(this.getDirection());
        }
    }

    /**
     * Checks if the generic conditions for an object to be loaded on to the TransportVehicle are fulfilled
     * @param target the target to be loaded, is compared to the conditions.
     * @return true if conditions are met.
     * @throws Exception if conditions are not met.
     */
    private boolean genericConditionsMet(Transportable target) throws Exception {
        if (loadable.loadableConditionsMet(this, target)) {
            if (isStationary()) {
               return true;
            } else {
                throw new Exception("The TransportVehicle is not stationary.");
            }
        } else {
            throw new Exception("Transportable cannot be loaded at the moment.");
        }
    }

    protected abstract boolean modelSpecificConditionsMet(Transportable target);

    /**
     * Makes sure the TransportVehicle can be loaded.
     * @param target the Transportable to be loaded.
     * @return true if conditions are met.
     * @throws Exception if conditions are not met.
     */
    private boolean canLoad(Transportable target) throws Exception {
        if (modelSpecificConditionsMet(target)) {
            return genericConditionsMet(target);
        } else {
            return false;
        }
    }

    /**
     * Loads the Transportable onto the TransportVehicle.
     * @param target the Transportable to be loaded.
     * @throws Exception if the Transportable cannot be loaded due to it not fulfilling the conditions.
     */
    public void load(Transportable target) throws Exception {
        if (canLoad(target)) {
            loadable.calculateLoad(target);
        }
    }

    /**
     * Unloads the target from the TransportVehicle.
     * @param target the Transportable to be unloaded.
     * @throws Exception if the Transportable is not available to be unloaded.
     */
    public void unload(Transportable target) throws Exception {
        loadable.unload(target);
    }

    double speedFactor() {
        if (allowedToMove) {
            return enginePower * 0.005;
        } else {
            return 0; // Makes it so that incrementSpeed returns 0
        }
    }

}
