
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author orel
 */
public class BlockFillColor implements BlockFiller {
    private Color color;

    /**
     * Constructor.
     *
     * @param fillColor - the color to fill with the block
     */
    public BlockFillColor(Color fillColor) {
        this.color = fillColor;
    }

    @Override
    public void drawOn(DrawSurface d, Rectangle rect) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
    }
}
