
import biuoop.DrawSurface;

/**
 * @author orel
 */
public class LevelBackground implements Sprite {

    // Property
    private SpriteCollection gameSprites;

    /**
     * Constructor.
     */
    public LevelBackground() {
        gameSprites = new SpriteCollection();
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.gameSprites.drawAllOn(d);
    }


    @Override
    public void timePassed(double dt) {
    }

    /**
     * Adds a new sprite to the game.
     *
     * @param s - the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.gameSprites.addSprite(s);
    }


}