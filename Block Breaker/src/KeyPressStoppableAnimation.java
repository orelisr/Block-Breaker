
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author orel
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation decoreatedAnime;
    private KeyboardSensor ks;
    private String keyToStop;
    private boolean shouldStop;
    private boolean isPressed;

    /**
     * A Constructor.
     *
     * @param sensor - keyboard sensor
     * @param k - key to stop the animation
     * @param animation - the animation of stopping.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String k,
                                      Animation animation) {

        this.isPressed = true;
        this.ks = sensor;
        this.decoreatedAnime = animation;
        this.shouldStop = false;
        this.keyToStop = k;
    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.decoreatedAnime.doOneFrame(d);
        if ((this.ks.isPressed(this.keyToStop))
                && (!this.isPressed)) {
            this.shouldStop = true;
        } else if (!this.ks.isPressed(this.keyToStop)) {
            this.isPressed = false;
        }
    }


    /**
     * Start over the animation.
     */
    public void startOverAnimation() {
        this.shouldStop = false;
    }
}