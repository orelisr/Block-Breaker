
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * @author orel
 */
public class GameFlow {

    private KeyboardSensor  ks;
    private AnimationRunner animationRunner;
    private Counter         lives;
    private GUI             gui;
    private HighScoresTable gameHS;
    private Counter         scores;


    /**
     * Constructor.
     *
     * @param ar - the animation runner
     * @param kss - the keyboard sensor
     * @param guii - our gui
     * @param highScoresTable - high scores table.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor kss, GUI guii,
            HighScoresTable highScoresTable) {
        this.ks = kss;
        this.lives = new Counter(4);
        this.gameHS = highScoresTable;
        this.scores = new Counter(0);
        this.gui = guii;
        this.animationRunner = ar;
    }

    /**
     * Runs the levels given.
     *
     * @param currentLevels - levels to run
     */
    public void runLevels(List<LevelInformation> currentLevels) {
        this.lives = new Counter(4);
        this.scores = new Counter(0);
        for (LevelInformation ourLevelInfo : currentLevels) {

            GameLevel ourLevel = new GameLevel(ourLevelInfo, this.ks,
                    this.animationRunner, this.gui, this.scores,
                    this.lives);
            ourLevel.initialize();
            //runs the level until compllition
            while (!ourLevel.isComplete() && this.lives.getValue() > 0) {
                ourLevel.playOneTurn();

                //checks if the player died
                if (!ourLevel.isComplete()) {
                    this.lives.decrease(1);
                }
            }

            if (this.lives.getValue() == 0) {
                break;
            }

            if (ourLevel.isComplete()) {
                this.scores.increase(100);
            }

        }

        boolean win = false;

        // If the player lost
        if (this.lives.getValue() == 0) {
            win = true;
        }

        EndScreen end = new EndScreen(win, this.scores,
                currentLevels.get(currentLevels.size() - 1).getBackground());
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.ks, KeyboardSensor.SPACE_KEY, end));

        if (this.gameHS.getRank(this.scores.getValue()) >= 0) {

            DialogManager dg = this.gui.getDialogManager();
            String name = dg.showQuestionDialog("Name",
                    "What is the player's name?", "");
            System.out.println(name);
            ScoreInfo newScore = new ScoreInfo(name,
                    this.scores.getValue());
            this.gameHS.add(newScore);
        }

        HighScoresAnimation tableDraw = new HighScoresAnimation(
                this.gameHS);
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.ks, KeyboardSensor.SPACE_KEY, tableDraw));
    }
}