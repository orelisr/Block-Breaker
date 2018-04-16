
/**
 * @author orel
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor of Block Remover.
     * @param ourGame - current game.
     * @param remainingBlocks - remaining blocks in the game
     */
    public BlockRemover(GameLevel ourGame, Counter remainingBlocks) {
        this.game = ourGame;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(BlockInterface beingHit, Ball hitter) {
        if (beingHit.getAmountOfHits() == 0) {
            this.remainingBlocks.decrease(1);
            beingHit.removeFromGame(game);
        }
    }
}