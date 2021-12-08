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
    public void setCoordinates(int x, int y) {
        this.pos.setLocation(x, y);
    }

    @Override
    public int getX() {
        return (int) pos.getX();
    }

    @Override
    public int getY() {
        return (int) pos.getY();
    }

    @Override
    public void setDirection(int direction) {
        this.pos.setDirection(direction);
    }

    @Override
    public int getDirection() {
        return this.pos.getDirection();
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
