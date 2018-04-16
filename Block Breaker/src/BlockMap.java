
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author orel
 */
public class BlockMap {
    private BlocksFromFiles blocksFromFiles;
    private Map<String, Map<String, String>> confirmedBlocksMap;
    private Map<String, BlockInterface> blocks;


    /**
     * Map Constructor.
     *
     * @param filemap - the mapping of the blocks.
     */
    public BlockMap(BlocksFromFiles filemap) {
        this.blocksFromFiles = filemap;
        this.confirmedBlocksMap = this
                .checkBlocks(this.blocksFromFiles.getBlocksMap());
        this.blocks = new TreeMap<>();
        this.createBlocks();
    }


    /**
     * Checks the block details.
     *
     * @param blockMapProperties - our map of details
     * @return if all the details are ok
     */
    private boolean checkProperties(Map<String, String> blockMapProperties) {
        this.makeBlockSimple(blockMapProperties);
        if (!blockMapProperties.containsKey("height")) {
            return false;
        }


        if (!blockMapProperties.containsKey("hit_points")) {
            return false;
        }

        if (!blockMapProperties.containsKey("width")) {
            return false;
        }

        if (!blockMapProperties.containsKey("fill")) {
            int hitPoints = Integer.parseInt(blockMapProperties.get("hit_points"));
            for (int i = 1; i <= hitPoints; i++) {
                String fillCheck = "fill-" + i;
                //checks for fill-i
                if (!blockMapProperties.containsKey(fillCheck)) {
                    return false;
                }
            }

            blockMapProperties.put("fill", blockMapProperties.get("fill-1"));
            blockMapProperties.put("fill-", blockMapProperties.get("fill-1"));
        } else {
            boolean changeFill = false;

            int hitPoints = Integer.parseInt(blockMapProperties.get("hit_points"));
            for (int i = 1; i <= hitPoints; i++) {
                String fillCheck = "fill-" + i;
                if (blockMapProperties.containsKey(fillCheck)) {
                    changeFill = true;
                }
            }
            if (changeFill) {
                blockMapProperties.put("fill-", blockMapProperties.get("fill-1"));
            }
        }

        return true;
    }

    /**
     * Creates a simple block.
     *
     * @param simpleBlockMap - a map to use when creating the block.
     */
    private void makeBlockSimple(Map<String, String> simpleBlockMap) {
        Map<String, String> defaultsMap = this.blocksFromFiles
                .getPairsMap();
        for (Map.Entry<String, String> entery : defaultsMap.entrySet()) {
            if (!simpleBlockMap.containsKey(entery.getKey())) {
                simpleBlockMap.put(entery.getKey(), entery.getValue());
            }
        }
    }

    /**
     * .
     * @return map of seperators
     */
    public Map<String, Integer> getSeperatorsMap() {
        return this.blocksFromFiles.getSeperatorsMap();
    }

    /**
     * .
     *
     * @return the blocks list from the map.
     */
    public Map<String, BlockInterface> getBlocks() {
        return this.blocks;
    }

    /**
     * Checks the blocks in our given map.
     *
     * @param blocksMapToCreate - The blocks map
     * @return a map that maps only the right balls.
     */
    private Map<String, Map<String, String>> checkBlocks(
            Map<String, Map<String, String>> blocksMapToCreate) {
        Map<String, Map<String, String>> copy = new TreeMap<String, Map<String, String>>();

        for (Map.Entry<String, Map<String, String>> entery : blocksMapToCreate
                .entrySet()) {
            if (this.checkProperties(entery.getValue())) {
                copy.put(entery.getKey(), entery.getValue());
            }
        }
        return copy;
    }


    /**
     * Creates the blocks with the given details from the files.
     */
    private void createBlocks() {
        for (Map.Entry<String, Map<String, String>> blockDetails : this.confirmedBlocksMap
                .entrySet()) {

            double height = Double
                    .parseDouble(blockDetails.getValue().get("height"));
            double width = Double
                    .parseDouble(blockDetails.getValue().get("width"));
            Rectangle rect = new Rectangle(new Point(0, 0), width, height);
            BlockInterface blockInt = new Block(rect);

            int numOfHits = Integer
                    .parseInt(blockDetails.getValue().get("hit_points"));
            String filler = blockDetails.getValue().get("fill");
            blockInt.setAmountOfHits(numOfHits);
            FillerMaker fillerM = new FillerMaker();
            blockInt.setFiller(fillerM.fillerFromString(filler));
            if (blockDetails.getValue().containsKey("stroke")) {
                ColorParser parser = new ColorParser();
                blockInt = new BlockFrameDecorator(blockInt, parser
                        .colorFromString(blockDetails.getValue().get("stroke")));
            }

            if (blockDetails.getValue().containsKey("fill-")) {
                ArrayList<BlockFiller> listOfFillers = new ArrayList<>();
                for (int i = 1; i <= blockInt.getAmountOfHits(); i++) {
                    if (blockDetails.getValue().containsKey("fill-" + i)) {
                        listOfFillers.add(new FillerMaker().fillerFromString(
                                blockDetails.getValue().get("fill-" + i)));
                    } else {
                        listOfFillers.add(new FillerMaker().fillerFromString(
                                blockDetails.getValue().get("fill")));
                    }
                }
                blockInt = new BlockFillDecorator(blockInt, listOfFillers);
            }
            this.blocks.put(blockDetails.getKey(), blockInt);
        }
    }
}