
import java.util.Map;

/**
 * @author orel
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer>        separatorsMap;
    private Map<String, BlockInterface> blocks;

    /**
     * Constructor.
     *
     * @param blockMapping - block map
     */
    public BlocksFromSymbolsFactory(BlockMap blockMapping) {
        this.separatorsMap = blockMapping.getSeperatorsMap();
        this.blocks = blockMapping.getBlocks();
    }

    /**
     * Gets the space width of the seperator.
     *
     * @param s - string seperator
     * @return the int width of the seperator
     */
    public int getSpaceWidth(String s) {
        if (this.isSpaceSymbol(s)) {
            return this.separatorsMap.get(s);
        }
        return 0;
    }

    /**
     * Returns a new block in the position xpos, ypos.
     *
     * @param s - to check
     * @param xpos the x coordinate.
     * @param ypos the y coordinate.
     * @return the new block
     */
    public BlockInterface getBlock(String s, int xpos, int ypos) {
        if (this.isBlockSymbol(s)) {
            BlockInterface newBlock = this.blocks.get(s).copy();
            newBlock.setNewYCord(ypos);
            newBlock.setNewXCord(xpos);
            return newBlock;
        }
        return null;
    }

    /**
     * checks if the string is a seperator.
     *
     * @param s - string to check
     * @return true if seperator, false if not
     */
    public boolean isSpaceSymbol(String s) {
        return this.separatorsMap.containsKey(s);
    }

    /**
     * Checks if it is a block.
     * @param s - string to check
     * @return true if block, false if not
     */
    public boolean isBlockSymbol(String s) {
        return this.blocks.containsKey(s);
    }


}
