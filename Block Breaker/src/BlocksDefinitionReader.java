import java.io.Reader;

/**
 * @author orel
 */
public class BlocksDefinitionReader {

    /**
     * Gets the blocksfromsymbolsfactory map from the file.
     *
     * @param reader - our file reader
     * @return a map with the details of the file
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) {
        return new BlocksFromSymbolsFactory(
                new BlockMap(new BlocksFromFiles(reader)));
    }
}