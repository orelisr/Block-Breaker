
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author orel
 */
public class PauseScreen implements Animation {
    private Sprite  background;

    /**
     * Constructor.
     * @param bg - background
     */
    public PauseScreen(Sprite bg) {
        this.background = bg;
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
        d.setColor(Color.WHITE);
        d.drawText(50, d.getHeight() / 2, "paused -- press space to continue",
                30);
    }


    /**
     * Start over the animation.
     */
    public void startOverAnimation() {
    }
}