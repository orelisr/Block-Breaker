
import biuoop.DrawSurface;


import java.awt.Color;

/**
 * @author orel
 */
public class ScoreIndicator implements Sprite {

    private Color colorOfText;
    private Counter scoreCounter;

    /**
     * Constructor.
     *
     * @param scoreCount - score counter
     * @param ourColor - color
     */
    public ScoreIndicator(Counter scoreCount, Color ourColor) {
        this.scoreCounter = scoreCount;
        this.colorOfText = ourColor;
    }

    @Override
    public void drawOn(DrawSurface d) {
        String numToString = Integer.toString(this.scoreCounter.getValue());
        d.setColor(this.colorOfText);
        d.drawText(352, 26, "Score: " + numToString, 21);
    }


    @Override
    public void timePassed(double dt) {
    }
}