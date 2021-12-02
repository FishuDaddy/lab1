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

    @Override
    public void move(){
        this.x += getCurrentSpeed() * Math.cos(Math.toRadians(this.dir));
        this.y += getCurrentSpeed() * Math.sin(Math.toRadians(this.dir));
        for (Transportable transported : loadable.onTransport) {
            transported.setCoordinates(this.x, this.y);
        }
    }

    @Override
    public void turnLeft(){
        this.dir += 45;
        this.dir %= 360;
        for (Transportable transported : loadable.onTransport) {
            transported.setDirection(this.getDirection());
        }
    }
    @Override
    public void turnRight() {
        this.dir -= 45;
        this.dir %= 360;
        for (Transportable transported : loadable.onTransport) {
            transported.setDirection(this.getDirection());
        }
    }

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

    private boolean canLoad(Transportable target) throws Exception {
        if (modelSpecificConditionsMet(target)) {
            return genericConditionsMet(target);
        } else {
            return false;
        }
    }

    public void load(Transportable target) throws Exception {
        if (canLoad(target)) {
            loadable.calculateLoad(target);
        }
    }
    public void unload(Transportable target) throws Exception {
        loadable.unload(target);
    }

    double speedFactor() {
        if (allowedToMove) {
            return enginePower * 0.3 * ((double)(maxWeight/(weight + loadable.getWeight())));
        } else {
            return 0; // Makes it so that incrementSpeed returns 0
        }
    }

}

