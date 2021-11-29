/**
 * Represents a Ramp which can be raised and lowered
 *
 */

abstract public class Ramp implements Rampable {

    protected boolean platformRaised;

    protected void ramp() {
        platformRaised = false;
    }


    abstract public void raisePlatform() throws Exception;
    abstract public void lowerPlatform() throws Exception;

}