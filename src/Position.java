import java.awt.*;

public class Position extends Point {
    protected Point pos;
    protected int dir;

    public Position(int x, int y, int dir) {
        this.pos = new Point(x, y);
        this.dir = dir;
    }

    protected int getDirection() {
        return dir;
    }
    protected void setDirection(int direction) {
        dir = direction;
    }

    protected void incDirection(int direction) {
        dir += direction;
        dir %= 360;
    }
}

