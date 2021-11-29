/**
 * Represents a Ramp which can be raised and lowered
 *
 */

abstract public class Ramp {

    protected boolean platformRaised; // Is the platform activated or not?**

    /**
     * Constructor that creates the base ramp which starts as not raised
     */
    protected void ramp() {
        platformRaised = false;
    }

}