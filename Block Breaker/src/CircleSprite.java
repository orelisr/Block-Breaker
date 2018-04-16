
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author orel
 */
public class CircleSprite implements Sprite {

    private int radius;
    private Point center;
    private java.awt.Color circleColor;
    private boolean filled;

    /**
     * Constructor.
     *
     * @param p - center point
     * @param radius - circle radius
     * @param color - color
     * @param filled - to fill or not to fill, that's the question
     */
    public CircleSprite(Point p, int radius, Color color,
                        boolean filled) {
        this.center = p;
        this.radius = radius;
        this.circleColor = color;
        this.filled = filled;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.circleColor);
        if (this.filled) {
            d.fillCircle((int) this.center.getX(), (int) this.center.getY(),
                    this.radius);
        } else {
            d.drawCircle((int) this.center.getX(), (int) this.center.getY(),
                    this.radius);
        }
    }

    @Override
    public void timePassed(double dt) {
    }
}