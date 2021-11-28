/**
 * Interface for implementation of a liftable Ramp
 */

public interface Rampable {
    int getPlatformAngle();
    void raisePlatform() throws Exception;
    void lowerPlatform() throws Exception;

}
