
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author orel
 */
public class BlocksFromFiles {
    private Map<String, Integer>             separatorsMap;
    private Map<String, Map<String, String>> blocksMap;
    private Map<String, String>              defaultsMap;


    /**
     * Constructor.
     * @param reader - reader from the file.
     */
    public BlocksFromFiles(Reader reader) {
        this.separatorsMap = new TreeMap<>();
        this.defaultsMap = new TreeMap<>();
        this.blocksMap = new TreeMap<>();

        this.load(reader);
    }


    /**
     * Takes from the file current pair of details of symbols.
     * @param details - encoding array to be used when getting the details from the file.
     */
    public void getSeperators(String[] details) {
        int value = -1;
        String symbol = null;
        for (int i = 0; i < details.length; ++i) {
            String[] lineDef = details[i].split(":");
            if (lineDef[0].equals("symbol")) {
                symbol = lineDef[1];
            }

            if (lineDef[0].equals("width")) {
                value = Integer.parseInt(lineDef[1]);
            }
        }
        if ((symbol != null) && (value != -1)) {
            this.separatorsMap.put(symbol, value);
        } else {
            System.out.print("The line: ");
            for (int i = 0; i < details.length; i++) {
                System.out.print(details[i] + " ");
            }
            System.out.println("Can't process file");
        }
    }

    /**
     * Gets pair of details from each line.
     * @param currentLine - line for pairs and values.
     */
    private void getPairs(String[] currentLine) {
        for (int i = 1; i < currentLine.length; i++) {
            String value = null;
            String property = null;
            String[] lineDef = currentLine[i].split(":");
            property = lineDef[0];
            value = lineDef[1];
            this.defaultsMap.put(property, value);
        }
    }


    /**
     * load function to get the details from the file.
     * @param reader - file reader
     */
    private void load(Reader reader) {
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(reader);
            String currentLine = buffer.readLine();
            //splits lines to get the details in case default, bdef, sdef
            while (currentLine != null) {
                String[] def;

                if (currentLine.startsWith("default")) {
                    def = currentLine.split("\\s+");
                    this.getPairs(def);

                } else if (currentLine.startsWith("bdef")) {
                    def = currentLine.split("\\s+");
                    this.getBlocksFromString(def);

                } else if (currentLine.startsWith("sdef")) {
                    def = currentLine.split("\\s+");
                    this.getSeperators(def);
                }

                currentLine = buffer.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("File opening error");
        } catch (IOException e) {
            System.out.println("IO exception in LOAD");
        }
    }

    /**
     * .
     * @return seperators map
     */
    public Map<String, Integer> getSeperatorsMap() {
        return this.separatorsMap;
    }

    /**
     * .
     * @return returns the pairs map
     */
    public Map<String, String> getPairsMap() {
        return this.defaultsMap;
    }

    /**
     * .
     * @return returns the blocks map
     */
    public Map<String, Map<String, String>> getBlocksMap() {
        return this.blocksMap;
    }


    /**
     * The main loading of symbols from the file. Takes symbols that the block could use.
     * @param currentLine - line to be parsed.
     */
    private void getBlocksFromString(String[] currentLine) {
        String symbol;
        if (currentLine.length >= 2) {
            //splits the line
            String[] lineDef = currentLine[1].split(":");
            //check if it equals symbol
            if (lineDef[0].equals("symbol")) {
                symbol = lineDef[1];
                Map<String, String> blockMap = new TreeMap<>();
                for (int i = 2; i < currentLine.length; i++) {
                    String[] pairs = currentLine[i].split(":");
                    // If propValuePair is at list a pair
                    if (pairs.length >= 2) {
                        blockMap.put(pairs[0],
                                pairs[1]);
                    }
                }
                this.blocksMap.put(symbol, blockMap);
            } else {
                System.out.print("The line: ");
                for (int i = 0; i < currentLine.length; i++) {
                    System.out.print(currentLine[i] + " ");
                }
                System.out.println("Can't process the file");
            }
        }
    }
}