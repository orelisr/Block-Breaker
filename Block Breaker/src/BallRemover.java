

/**
 * @author orel
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param ourGame - game
     * @param remainingBalls - remaining balls in the game.
     */
    public BallRemover(GameLevel ourGame, Counter remainingBalls) {
        this.game = ourGame;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Notifies the hit listener that event has happened.
     * @param block - death block
     * @param hitter - ball to remove
     */
    public void hitEvent(BlockInterface block, Ball hitter) {
            hitter.removeFromGame(game);
            this.remainingBalls.decrease(1);
    }
}