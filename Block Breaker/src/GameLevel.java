
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * @author orel
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private boolean isRun;
    private biuoop.KeyboardSensor keyboard;
    private Counter remainingBlocks;
    private Counter ballsCounter;
    private Counter scoreCounter;
    private BlockRemover blockRemover;
    private Paddle ourPaddle;
    private AnimationRunner runner;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrack;
    private ScoreIndicator theScore;
    private LivesIndicator lives;
    private Sprite background;
    private LevelInformation levelInfo;
    private LevelNames levelname;

    /**
     * Constructor. Initializes the game.
     * @param level - game level
     * @param ks - keyboard sensor
     * @param ar AnimationRunner
     * @param theGui GUI
     * @param score Counter of scores
     * @param ourLives Counter of lives
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks,
                     AnimationRunner ar, GUI theGui, Counter score, Counter ourLives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.scoreCounter = score;
        this.theScore = new ScoreIndicator(this.scoreCounter,
                java.awt.Color.BLACK);
        this.sprites.addSprite(theScore);
        this.lives = new LivesIndicator(ourLives, Color.BLACK);
        this.sprites.addSprite(this.lives);
        this.levelInfo = level;
        this.background = this.levelInfo.getBackground();
        this.keyboard = ks;
        this.runner = ar;
        this.gui = theGui;
        this.levelname = new LevelNames(this.levelInfo.levelName(),
                java.awt.Color.BLACK);
        this.sprites.addSprite(levelname);
    }

    /**
     * remove a spritne.
     *
     * @param s - sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * initialize the game
     * bloc1ks, balls, borders, number of hits, paddle and enviorment.
     */
    public void initialize() {

        // Adding blocks
        List<BlockInterface> blocksLevel = this.levelInfo.blocks();

        for (BlockInterface block : blocksLevel) {
            block.addToGame(this);
        }

        // Return the environment to it's initial state.
        this.environment.restartGameEnvironment();
        this.ballsCounter = new Counter(0);
        this.remainingBlocks = new Counter(0);
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);

        this.scoreTrack = new ScoreTrackingListener(this.scoreCounter);
        //the death block
        for (Collidable c : this.environment.getColidables()) {
            if (!(c instanceof DeathBlock)) {
                ((BlockInterface) c).addHitListener(this.blockRemover);
                ((BlockInterface) c).addHitListener(this.scoreTrack);
            }
        }

        this.remainingBlocks.increase(this.levelInfo.numberOfBlocksToRemove());
        this.ballRemover = new BallRemover(this, this.ballsCounter);
        rec();
        this.isRun = true;
        this.runner = new AnimationRunner(this.gui, 60);
    }

    /**
     * Drawing rectangels.
     */
    public void rec() {
        Rectangle recUp = new Rectangle(new Point(0, 30), 800, 30);
        Block upperBlock = new Block(recUp);
        upperBlock.setColor(java.awt.Color.DARK_GRAY);
        upperBlock.addToGame(this);

        Rectangle recDown = new Rectangle(new Point(30, 599), 800, 30);
        DeathBlock downBlock = new DeathBlock(recDown);
        downBlock.setColor(java.awt.Color.DARK_GRAY);
        downBlock.addToGame(this);

        Rectangle recRight = new Rectangle(new Point(0, 30), 30, 570);
        Block rightBlock = new Block(recRight);
        rightBlock.setColor(java.awt.Color.DARK_GRAY);
        rightBlock.addToGame(this);

        Rectangle recLeft = new Rectangle(new Point(770, 30), 30, 570);
        Block leftBlock = new Block(recLeft);
        leftBlock.setColor(java.awt.Color.DARK_GRAY);
        leftBlock.addToGame(this);

        downBlock.addHitListener(this.ballRemover);

    }

    /**
     * Adds a new collidable.
     *
     * @param c - the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove collidable.
     *
     * @param c - the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a sprite.
     *
     * @param s - removes a sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }



    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(this.runner.getDt());
        if (this.remainingBlocks.getValue() == 0) {
            this.isRun = false;
        }
        frame(d);

    }

    /**
     * Responsible for the frame.
     * @param d - surface
     */
    public void frame(DrawSurface d) {

        if (this.ballsCounter.getValue() == 0) {
            this.isRun = false;
        }

        if (this.keyboard.isPressed("p")) {
            PauseScreen pause = new PauseScreen(this.background);
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pause));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.isRun;
    }


    /**
     * Game plays a single turn.
     */
    public void playOneTurn() {

        int middle = (800 - this.levelInfo.paddleWidth()) / 2;
        if (this.ourPaddle == null) {
            Rectangle rect = new Rectangle(new Point(middle, 570),
                    this.levelInfo.paddleWidth(), 10);
            this.ourPaddle = new Paddle(rect, this.levelInfo.paddleSpeed(),
                    760 - this.levelInfo.paddleWidth(), 40, gui,
                    Color.ORANGE);
            this.ourPaddle.addToGame(this);
        } else {
            this.ourPaddle.movePaddleToX(middle);
        }
        playOneTurnFirstHelper();

        this.ballsCounter.increase(this.levelInfo.numberOfBalls());

        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.isRun = true;
        this.runner.run(this);
    }

    /**
     * Helper for the playoneturn.
     */
    public void playOneTurnFirstHelper() {

        int ballsSpace = (this.levelInfo.paddleWidth() - 20)
                / (this.levelInfo.numberOfBalls() + 1);

        for (int i = 1; i <= this.levelInfo.numberOfBalls(); i++) {
            Point p = new Point(
                    this.ourPaddle.getCollisionRectangle().getUpperLeft().getX() + 10
                            + i * ballsSpace,
                     this.ourPaddle.getCollisionRectangle().getUpperLeft().getY() - 5);
            Ball b = new Ball((int) p.getX(), (int) p.getY(), 5,
                    java.awt.Color.WHITE);
            Velocity vel = this.levelInfo.initialBallVelocities().get(i - 1);
            secondHelper(vel, b);
        }
    }

    /**
     * Helps the playOneTurn.
     * @param b - the ball
     * @param vel - the velocity
     */
    public void secondHelper(Velocity vel, Ball b) {
        if (vel.getDy() > 0) {
            vel = new Velocity(vel.getDx(), -vel.getDy());
            if (vel.getDx() == 0 && vel.getDy() <  -250) {
                vel = new Velocity(vel.getDx(), -250);
            }
        }
        b.setVelocity(vel);
        b.setArea(new Point(0, 0), new Point(800, 600));
        b.setGameEnvironment(this.environment);
        b.addToGame(this);

    }


    /**
     * if the game is complete.
     * @return if the game is done
     */
    public boolean isComplete() {
        if (this.remainingBlocks.getValue() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void startOverAnimation() {
    }
}