
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author orel
 */
public class CountdownAnimation implements Animation {

    private boolean          stop;
    private SpriteCollection gameScreen;
    private int              count;
    private double           timeOfCount;
    private double           numOfSeconds;
    private long             ending;


    /**
     * Constructor.
     *
     * @param numOfSeconds - seconds to count
     * @param countFrom - counting from
     * @param sprites - our sprites
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
            SpriteCollection sprites) {
        this.stop = false;
        this.numOfSeconds = numOfSeconds;
        this.count = countFrom + 1;
        this.gameScreen = sprites;
        this.ending = -1;
        this.timeOfCount = this.numOfSeconds * 1000 / countFrom;

    }


    /**
     * Should the animation stop.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
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
        this.gameScreen.drawAllOn(d);

        if (this.ending == -1) {
            this.ending = System.currentTimeMillis()
                    + (long) this.timeOfCount;
        }

        d.setColor(Color.GREEN);
        if (this.count == 1) {
            d.drawText(400, 400, "START!", 40);
        } else {
            d.drawText(400, 400, String.valueOf(this.count - 1) + "..",
                    40);
        }

        if (this.ending <= System.currentTimeMillis()) {
            this.count--;
            this.ending = System.currentTimeMillis()
                    + (long) this.timeOfCount;
        }

        if (this.count == 0) {
            this.stop = true;
        }
    }

}