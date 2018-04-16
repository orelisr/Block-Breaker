
import biuoop.DrawSurface;


import java.awt.Color;

/**
 * @author orel
 */
public class RectangleSprite implements Sprite {
    private Color recColor;
    private Rectangle rectangle;
    private boolean fill;

    /**
     * Constructor.
     *
     * @param rect - rectangle.
     * @param colorRec - rectangle color
     * @param f - decorate and fill or not
     */
    public RectangleSprite(Rectangle rect, Color colorRec, boolean f) {
        this.recColor = colorRec;
        this.rectangle = rect;
        this.fill = f;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.recColor);
        if (this.fill) {
            d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        } else {
            d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        }
    }

    @Override
    public void timePassed(double dt) {
    }
}
