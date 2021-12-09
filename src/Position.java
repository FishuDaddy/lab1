public class Position implements Rotatable {
    private double x;
    private double y;
    private double direction;

    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
        this.direction = 0;
    }
    public Position(double x, double y, double direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    protected void setX(double x) {
        this.x = x;
    }

    protected void setY(double y) {
        this.y = y;
    }


    @Override
    public void setDirection(double direction) {
        this.direction = direction;
    }

    @Override
    public double getDirection() {
        return direction;
    }

    protected void incDirection(double direction) {
        this.direction = this.direction + direction;
    }
}
