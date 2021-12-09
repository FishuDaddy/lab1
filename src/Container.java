/**
 * Generic transportable object.
 */
public class Container implements Transportable {
    private final int weight;
    private boolean beingTransported;
    protected Position pos;
    public Container(int x, int y, int weight, int angle) {
        this.weight = weight;
        beingTransported = false;
        this.pos = new Position(x, y, angle);
    }

    @Override
    public void setCoordinates(double x, double y) {
        this.pos.setCoordinates(x, y);
    }

    @Override
    public double getX() {
        return (int) pos.getX();
    }

    @Override
    public double getY() {
        return (int) pos.getY();
    }

    @Override
    public void setDirection(double direction) {
        pos.setDirection(direction);
    }

    @Override
    public double getDirection() {
        return pos.getDirection();
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
