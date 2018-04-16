
import java.util.ArrayList;
import java.util.List;

/**
 * @author orel
 */
public class LevelInformationFactory implements LevelInformation {

    private int                  numberOfBalls;
    private List<Velocity>       ballSpeeds;
    private Sprite               background;
    private List<BlockInterface> blocksList;
    private int                  paddleSpeed;
    private int                  paddleWidth;
    private String               levelName;
    private int                  numberOfBlocksToRemove;

    /**
     * Constructor, sets new arrays.
     */
    public LevelInformationFactory() {
        this.ballSpeeds = new ArrayList<>();
        this.blocksList = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Sets the amount of balls.
     *
     * @param balls - amount of balls
     */
    public void setNumberOfBalls(int balls) {
        this.numberOfBalls = balls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballSpeeds;
    }


    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Sets the paddle speed.
     *
     * @param speed - paddle speed
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }


    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Sets paddle width.
     *
     * @param width - the width of the paddle.
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }


    @Override
    public String levelName() {
        return this.levelName;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * Sets the ball speed.
     *
     * @param vel - the new ball velocity.
     */
    public void addBallVelocity(Velocity vel) {
        this.ballSpeeds.add(vel);
    }


    /**
     * Sets the level name.
     *
     * @param name - the level name.
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }


    @Override
    public List<BlockInterface> blocks() {
        return this.blocksList;
    }

    /**
     * Adds a block to the list.
     *
     * @param block - the new block.
     */
    public void addBlock(BlockInterface block) {
        this.blocksList.add(block);
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Set a new background.
     * @param bg - the new background
     */
    public void setBackground(Sprite bg) {
        this.background = bg;
    }


    /**
     * Sets the block to remove.
     *
     * @param amount - blocks to remove.
     */
    public void setSumberOfBlocksToRemove(int amount) {
        this.numberOfBlocksToRemove = amount;
    }
}