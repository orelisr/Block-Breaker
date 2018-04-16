
import biuoop.DrawSurface;

/**
 * @author orel
 */
public interface Animation {
    /**
     * Does one frame.
     *
     * @param d - drawsurface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should the animation stop or not.
     *
     * @return true or false
     */
    boolean shouldStop();

    /**
     * start over the animation.
     */
    void startOverAnimation();
}