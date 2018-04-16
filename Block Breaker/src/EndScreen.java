
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author orel
 */
public class EndScreen implements Animation {

    private boolean stop;
    private Counter score;
    private Sprite backRound;
    private boolean isWin;

    /**
     * Constructor.
     *
     * @param win - did they win.
     * @param c - current points
     * @param background - background
     */
    public EndScreen(boolean win, Counter c,
            Sprite background) {
        this.score = c;
        this.stop = false;
        this.isWin = win;
        this.backRound = background;
    }



    /**
     * Starts over the animation.
     */
    public void startOverAnimation() {
        this.stop = false;
    }

    /**
     * Does one frame.
     *
     * @param d - our drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        this.backRound.drawOn(d);
        d.setColor(Color.WHITE);
        // If the user has won
        if (isWin) {
            d.drawText(40, d.getHeight() / 2,
                    "You Win! Your score is: " + this.score.getValue(), 32);
        } else {
            d.drawText(40, d.getHeight() / 2,
                    "Game Over! Your score is: " + this.score.getValue(), 32);
        }
    }

    /**
     * Should the animation stop.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}