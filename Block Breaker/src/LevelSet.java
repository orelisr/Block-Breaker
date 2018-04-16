import java.util.List;

/**
 * @author orel
 */
public class LevelSet {
    private String levelName;
    private List<LevelInformation> levelsSet;

    /**
     * Constructor.
     *
     * @param name - level name
     * @param levels - our list of level information
     */
    public LevelSet(String name, List<LevelInformation> levels) {
        this.levelName = name;
        this.levelsSet = levels;
    }

    /**
     * .
     * @return the level list.
     */
    public List<LevelInformation> getLevelsSet() {
        return this.levelsSet;
    }

    /**
     * .
     * @return the level name
     */
    public String getName() {
        return this.levelName;
    }


}
