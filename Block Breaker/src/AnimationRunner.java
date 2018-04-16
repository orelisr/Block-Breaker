
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author orel
 */
public class AnimationRunner {
    private Sleeper sleeper;
    private GUI     gui;
    private int     framesPerSecond;

    /**
     * Constructor.
     *
     * @param gui - game gui
     * @param framesPerSecond - fps
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * get DT of the animation.
     *
     * @return division of the fps
     */
    public double getDt() {
        return 1 / (double) this.framesPerSecond;
    }

    /**
     * running the animation.
     *
     * @param animation - animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        animation.startOverAnimation();
        while (!animation.shouldStop()) {
            long timeToStart = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long timeUsed = System.currentTimeMillis() - timeToStart;
            long milliSecondLeftToSleep = millisecondsPerFrame - timeUsed;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}