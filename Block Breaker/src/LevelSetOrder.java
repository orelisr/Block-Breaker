
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author orel
 */
public class LevelSetOrder {

    /**
     * .
     * @param reader - our reader file
     * @return A map of the strings and levelset
     */
    public static Map<String, LevelSet> fromReader(java.io.Reader reader) {
        BufferedReader buffer;
        Map<String, LevelSet> levelSetMap = new TreeMap<>();
        int lineNumber = 1;
        try {
            buffer = new BufferedReader(reader);
            String line = buffer.readLine();
            String levelName = "";
            String key = "";

            while (line != null) {
                if (lineNumber % 2 == 1) {
                    String[] sprite = line.split(":");
                    key = sprite[0];
                    levelName = sprite[1];
                } else {
                    List<LevelInformation> levels = getLevelList(line);
                    if (levels != null) {
                        LevelSet set = new LevelSet(levelName, levels);
                        levelSetMap.put(key, set);
                    }
                }
                lineNumber++;
                line = buffer.readLine();
            }
        } catch (IOException e) {
            System.out.println("There was a problem with the file");
        }
        return levelSetMap;
    }

    /**
     * .
     * @param file - string file
     * @return A list of the level information.
     */
    private static List<LevelInformation> getLevelList(String file) {
        InputStreamReader in = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(file);
            in = new InputStreamReader(is);
            List<LevelInformation> levelList = LevelSpecificationReader
                    .fromReader(in);
            if (levelList != null) {
                return levelList;
            }
        } catch (Exception e) {
            System.out.println("File not found");
            if (in != null) {
                try {
                    in.close();
                } catch (Exception d) {
                    System.out.println("closing file problem");
                }
            }
        }
        return null;
    }
}