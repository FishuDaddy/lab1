/**
 * Represents a Ramp which can be raised and lowered
 *
 */
abstract public class Ramp {

    protected boolean platformUse; // Is the platform activated or not?**

    /**
     * Constructor that creates the base ramp which starts as not raised
     */
    protected void ramp() {
        platformUse = false;
    }

    public void switchState() {
        platformUse = !platformUse;
    }

    abstract protected void raiseRamp() throws Exception;
    abstract protected void lowerRamp() throws Exception;
}