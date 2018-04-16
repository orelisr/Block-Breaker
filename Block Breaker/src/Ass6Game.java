
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author orel
 */
public class Ass6Game {
    /**
     * Main game function.
     *
     * @param args - arguments from main, level_sets
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        String filename;
        HighScoresTable gameHS = new HighScoresTable(10);
        Map<String, LevelSet> levelSet = null;
        InputStreamReader in = null;

        File fileHS = new File("highscores");
        //if the tablescores exist
        if (fileHS.exists()) {
            try {
                gameHS.load(fileHS);
            } catch (IOException e) {
                System.out.println(
                        "Can't load highscores file");
            }
        } else { //doesn't exist
            try {
                gameHS.save(fileHS);
            } catch (IOException e) {
                System.out.println(
                        "Can't load highscores file");
            }
        }
        //checking if got arguments from main

        if (args.length != 0) {
            filename = args[0];
        } else {
            filename = "level_sets.txt";
        }

        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
            in = new InputStreamReader(is);
            levelSet = LevelSetOrder.fromReader(in);
        } catch (Exception e) {
            System.out.println("Can't read from file");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println(
                            "Can't close the file");
                }
            }
        }

        MenuAnimation<Task<Void>> levelsMenu = new MenuAnimation<>(
                "Choose a game", gui.getKeyboardSensor(),
                new OldLevelBackground().getBackground());

        final GameFlow gameflow = new GameFlow(runner, gui.getKeyboardSensor(), gui,
                gameHS);
        final Map<String, LevelSet> ourLevelSet = levelSet;

        if (levelSet != null) {
            for (Map.Entry<String, LevelSet> levelsMap : ourLevelSet
                    .entrySet()) {
                final Map.Entry<String, LevelSet> levelSetMap = levelsMap;
                levelsMenu.addSelection(levelsMap.getKey(),
                        levelsMap.getValue().getName(), new Task<Void>() {
                            @Override
                            public Void run() {
                                gameflow.runLevels(levelSetMap.getValue()
                                        .getLevelsSet());
                                return null;
                            }
                        });
            }
        }
        //our submenu
        Task<Void> subMenuLevels = new SubMenu<Void>(levelsMenu, runner);

        MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                "Welcome To The Game!", gui.getKeyboardSensor(),
                new OldLevelBackground().getBackground());

        menu.addSelection("s", "start a new game", subMenuLevels);

        final HighScoresTable finalTable = gameHS;
        final String finalFileName = "highscores";
        menu.addSelection("q", "quit", new Task<Void>() {
            public Void run() {
                File file = new File(finalFileName);
                try {
                    finalTable.save(file);
                } catch (IOException e) {
                    System.out.println(
                            "Can't open highscores file");
                }

                System.exit(0);
                return null;
            }
        });
        HighScoresAnimation hs = new HighScoresAnimation(gameHS);

        final Animation endScreen = new KeyPressStoppableAnimation(
                gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY, hs);
        final AnimationRunner finalRunner = runner;
        menu.addSelection("h", "see the high scores", new Task<Void>() {
            public Void run() {
                finalRunner.run(endScreen);
                return null;
            }
        });


        while (true) {
            runner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}