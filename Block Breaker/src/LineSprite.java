import biuoop.DrawSurface;


import java.awt.Color;

/**
 * @author orel
 */
public class LineSprite implements Sprite {
    private Line lineSprite;
    private Color color;

    /**
     * Constructor.
     *
     * @param line - the line
     * @param c - the color
     */
    public LineSprite(Line line, Color c) {
        this.color = c;
        this.lineSprite = line;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Line l = this.lineSprite;
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }

    @Override
    public void timePassed(double dt) {

    }

}
