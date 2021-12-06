/**
 * Generic transportable object.
 */
public class Container implements Transportable {
    private double x;
    private double y;
    private final int weight;
    private double angle;
    private boolean beingTransported;
    public Container(double x, double y, int weight, double angle) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.angle = angle;
        beingTransported = false;
    }

    @Override
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setDirection(double direction) {
        this.angle = direction;
    }

    @Override
    public double getDirection() {
        return(angle);
    }

    @Override
    public int getWeight() {
        return(weight);
    }

    @Override
    public boolean isBeingTransported() {
        return beingTransported;
    }

    @Override
    public boolean canBeLoaded() {
        return !beingTransported;
    }

    @Override
    public void setBeingTransportState(boolean state) {
        beingTransported = state;
    }
}
