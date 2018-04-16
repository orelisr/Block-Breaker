
import biuoop.DrawSurface;

/**
 * @author orel
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scores;

    /**
     * Constructor.
     *
     * @param scoresS - scores
     */
    public HighScoresAnimation(HighScoresTable scoresS) {
        this.scores = scoresS;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        int spaces = 1;
        d.drawText(d.getWidth() / 2 - 101, 71, "High Scores:", 33);
        d.drawText(d.getWidth() / 2 - 101, 121, "Name", 23);
        d.drawText(d.getWidth() / 2, 121, "Score", 23);


        for (ScoreInfo score : this.scores.getHighScores()) {
            d.drawText(d.getWidth() / 2 - 101, 121 + spaces * 20,
                    String.valueOf(score.getScore()), 20);
            d.drawText(d.getWidth() / 2, 121 + spaces * 20, score.getName(),
                    20);
            spaces++;
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public void startOverAnimation() {
    }
}
