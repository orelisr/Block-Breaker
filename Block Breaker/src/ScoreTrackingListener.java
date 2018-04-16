

/**
 * @author orel
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param score - score.
     */
    public ScoreTrackingListener(Counter score) {
       this.currentScore = score;
    }

    @Override
    public void hitEvent(BlockInterface beingHit, Ball hitter) {
        this.currentScore.increase(beingHit.getHitPoints());
    }
 }