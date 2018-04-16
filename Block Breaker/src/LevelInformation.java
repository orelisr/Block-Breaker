
import java.util.List;

/**
 * @author orel
 */
public interface LevelInformation {
    /**
     * .
     * @return the amount of balls in our level.
     */
    int numberOfBalls();

    /**
     * .
     * @return the width of our paddle.
     */
    int paddleWidth();



    /**
     * .
     * @return the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     *.
     * @return Returns the paddle speed.
     */
    int paddleSpeed();


    /**
     * .
     * @return Our list of blocks.
     */
    List<BlockInterface> blocks();

    /**
     * .
     * @return Remaining blocks to remove.
     */
    int numberOfBlocksToRemove();

    /**
     * .
     * @return the level name.
     */
    String levelName();

    /**
     * .
     * @return the background sprite.
     */
    Sprite getBackground();

}
