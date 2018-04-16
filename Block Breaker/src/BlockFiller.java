
import biuoop.DrawSurface;

/**
 * @author orel
 */
public interface BlockFiller {

    /**
     * Draws on the gui the block.
     *
     * @param d - the drawsurface
     * @param rect - our rectangle
     */
    void drawOn(DrawSurface d, Rectangle rect);
}
