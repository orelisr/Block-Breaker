import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author orel
 */
public class BlockFrameDecorator extends BlockDecorator {
    private Color blockColor;

    /**
     * Constructor.
     *
     * @param block - block to decorate
     * @param frameColor - frame color
     */
    public BlockFrameDecorator(BlockInterface block, Color frameColor) {
        super(block);
        this.blockColor = frameColor;
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(this.blockColor);
        Rectangle rectangle = this.getCollisionRectangle();
        Point p = new Point(rectangle.getUpperLeft().getX(), rectangle.getUpperLeft().getY());
        Rectangle rect = new Rectangle(p, rectangle.getWidth(), rectangle.getHeight());
        d.drawRectangle((int) p.getX(), (int) p.getY(), (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public BlockInterface copy() {
        Color c = this.blockColor;
        return new BlockFrameDecorator(this.getDecoratedBlock().copy(), c);
    }


}
