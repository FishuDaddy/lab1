import java.awt.*;

public class MAN extends TransportVehicle {
    private boolean rampDown;

    public MAN() {
        commonAssembler(2, 220, Color.white, "MAN", 7500);
        transportAssembler(44000, 12, 8, true);
        modelSpecificConstructor();
    }
    private void modelSpecificConstructor() {
        rampDown = false;
    }

    @Override
    protected boolean modelSpecificConditionsMet(Transportable target) {
        return (target instanceof TransportableCar);
    }
    public void toggleRamp() {
        rampDown = isStationary();
        allowedToMove = !rampDown;
    }

}
