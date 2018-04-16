
import java.io.FileNotFoundException;
import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orel
 */
public class HighScoresTable implements Serializable {

    private ArrayList<ScoreInfo> scores;
    private int                  size;

    /**
     * Constructor.
     *
     * @param sizeOfTable - the size of the table we need.
     */
    public HighScoresTable(int sizeOfTable) {
        this.size = sizeOfTable;
        this.scores = new ArrayList<>();

    }


    /**
     * Returns the current rank of the score given.
     * If the score can't be found, returns -1
     *
     * @param score - our score to check
     * @return the rank of the score
     */
    public int getRank(int score) {
        for (int i = 0; i < this.scores.size(); i++) {
            if (this.scores.get(i).getScore() < score) {
                return i;
            }
        }
        if (this.scores.size() < this.size()) {
            return this.scores.size();
        }
        return -1;
    }

    /**
     * Clearing our table.
     */
    public void clear() {
        this.scores.removeAll(this.scores);
    }

    /**
     * Our LOAD function to open the file.
     *
     * @param theFile - our file to load
     * @throws IOException - Exception.
     */
    public void load(File theFile) throws IOException {
        this.clear();
        HighScoresTable hl = HighScoresTable.loadFromFile(theFile);
        this.size = hl.size;
        this.scores = (ArrayList<ScoreInfo>) hl.getHighScores();
    }

    /**
     * Checks the file and updates our highscores according to the file.
     *
     * @param theFile - our highscores file
     * @return the highscores table
     */
    public static HighScoresTable loadFromFile(File theFile) {
        HighScoresTable hl;
        ObjectInputStream objectIS = null;
        try {
            objectIS = new ObjectInputStream(
                    new FileInputStream(theFile));
            hl = (HighScoresTable) objectIS.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Cant open highscores");
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("stream problem");
            return null;
        } catch (IOException e) {
            System.out.println("Failed IO");
            return null;
        } finally {
            try {
                if (objectIS != null) {
                    objectIS.close();
                }
            } catch (IOException e) {
                System.out.println("closing file");
            }
        }
        return hl;
    }


    /**
     * Add a new high score.
     *
     * @param newScore - score to add
     */
    public void add(ScoreInfo newScore) {
        int r = this.getRank(newScore.getScore());
        if (r != -1) {
            int index = 0;
            ScoreInfo temp = newScore;
            List<ScoreInfo> copy = new ArrayList<>(this.scores);
            for (ScoreInfo info : copy) {
                if (r == index) {
                    this.scores.set(index, newScore);
                    temp = info;
                } else if (r < index) {
                    this.scores.set(index, temp);
                    temp = info;
                }
                index++;
            }

            if (this.size > index) {
                this.scores.add(index, temp);
            }
        }
    }


    /**
     * Sves our table.
     *
     * @param theFile - our saved file
     * @throws IOException - in case can't saving
     */
    public void save(File theFile) throws IOException {
        ObjectOutputStream objectOS = null;
        try {
            if (theFile.exists()) {
                theFile.delete();
            }
            objectOS = new ObjectOutputStream(
                    new FileOutputStream(theFile));
            objectOS.writeObject(this);
        } catch (IOException e) {
            System.out.println("Can't save");
        } finally {
            try {
                if (objectOS != null) {
                    objectOS.close();
                }
            } catch (IOException e) {
                System.out.println("Can't close the highscores");
            }
        }
    }

    /**
     * .
     *
     * @return The size of the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the list of high scores.
     *
     * @return the list
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }


}