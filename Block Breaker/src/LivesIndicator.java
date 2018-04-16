import biuoop.DrawSurface;


import java.awt.Color;

/**
 * @author orel
 */
public class LivesIndicator implements Sprite {

    private Counter lives;
    private Color color;

    /**
     * Constructor.
     *
     * @param remainingLives - the lives currently.
     * @param c - color
     */
    public LivesIndicator(Counter remainingLives, Color c) {
        this.color = c;
        this.lives = remainingLives;
    }

    @Override
    public void timePassed(double dt) {
    }

    @Override
    public void drawOn(DrawSurface d) {
        String livesString = Integer.toString(this.lives.getValue());
        d.setColor(this.color);
        d.drawText(121, 21, "Lives: " + livesString, 20);
    }

}