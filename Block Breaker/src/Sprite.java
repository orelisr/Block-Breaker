
import biuoop.DrawSurface;

/**
 * @author orel
 */
public interface Sprite {
    /**
     * Draws a sprite.
     *
     * @param d - drawsurface
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that the time has passed.
     *
     * @param dt - differentiation of time to be used.
     */
    void timePassed(double dt);
}