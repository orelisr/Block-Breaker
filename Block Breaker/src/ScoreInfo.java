
import java.io.Serializable;

/**
 * @author orel
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int gameScore;

    /**
     * Constructor.
     *
     * @param theName - name of player
     * @param points - amount of point
     */
    public ScoreInfo(String theName, int points) {
        this.name = theName;
        this.gameScore = points;
    }


    @Override
    public int compareTo(ScoreInfo other) {
        //check scores and compare
        ScoreInfo otherScore = (ScoreInfo) other;
        if (this.getScore() > otherScore.getScore()) {
            return 1;
        } else if (this.getScore() == otherScore.getScore()) {
            return 0;
        } else {
            return -1;
        }
    }


    /**
     * .
     * @return the name of the score.
     */
    public String getName() {
        return this.name;
    }

    /**
     * .
     * @return the score
     */
    public int getScore() {
        return this.gameScore;
    }

}