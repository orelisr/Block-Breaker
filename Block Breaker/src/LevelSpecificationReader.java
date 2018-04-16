
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedList;
import java.util.TreeMap;


/**
 * @author orel
 */
public class LevelSpecificationReader {


    /**
     * .
     * @param reader - file reader
     * @return a list of level information.
     */
    public static List<LevelInformation> fromReader(Reader reader) {
        List<LevelInformation> levelsList = new ArrayList<>();

        BufferedReader buff;
        try {
            buff = new BufferedReader(reader);
            String line = buff.readLine();

            while (line != null) {
                if (!line.equals("") && (!line.contains("#"))) {
                    LevelInformationFactory levelInfo = loadLevel(buff, line);
                    if (levelInfo != null) {
                        levelsList.add(levelInfo);
                    }
                }
                line = buff.readLine();
            }
        } catch (IOException e) {
            System.out.println("loading file problem");
        }
        return levelsList;
    }


    /**
     * A factory of level information .
     * @param buffer - file buffer
     * @param theLine - current line to read
     * @return Levelinformationfactory of the file
     * @throws IOException IO reading problem
     */
    private static LevelInformationFactory loadLevel(BufferedReader buffer,
                                                   String theLine) throws IOException {
        Map<String, String> defMapLine = new TreeMap<>();
        List<String> blockDetail = new LinkedList<>();

        while (!theLine.contains("START_LEVEL")) {
            theLine = buffer.readLine();
        }
        theLine = buffer.readLine();

        getBlocks(theLine, defMapLine, buffer, blockDetail);

        theLine = buffer.readLine();
        while (!theLine.contains("END_LEVEL")) {
            theLine = buffer.readLine();
        }
        LevelInformationFactory level = levelConfigurationToLevel(defMapLine,
                blockDetail);
        if (level != null) {
            return level;
        }
        return null;
    }

    /**
     * Gets the blocks from the file.
     * @param theLine - the line reading
     * @param defMapLine - map of definitions
     * @param buffer - buffer reader
     * @param blockDetail - list of block details
     */
    public static void getBlocks(String theLine, Map<String, String> defMapLine, BufferedReader buffer
                    , List<String> blockDetail) {

        try {
            while (!theLine.contains("START_BLOCKS")) {
                if (!theLine.contains("#")) {
                    String[] splitPair = theLine.split(":");
                    defMapLine.put(splitPair[0], splitPair[1]);
                }
                theLine = buffer.readLine();
            }

            theLine = buffer.readLine();

            while (!theLine.contains("END_BLOCKS")) {
                if (!theLine.contains("#")) {
                    blockDetail.add(theLine);
                    theLine = buffer.readLine();
                }
            }
        } catch (IOException e) {
            ;
        }
    }







    /**
     * Gets a map of configurations, and returns the level information of the details.
     * @param defMapLine is the map of the defenitions.
     * @param blockDetail is the map of the block organization.
     * @return the level information setter that represents
     * the level that the map and the list represents.
     */
    private static LevelInformationFactory levelConfigurationToLevel(
            Map<String, String> defMapLine,
            List<String> blockDetail) {
        boolean levelWorks = true;
        LevelInformationFactory level = new LevelInformationFactory();

        if (defMapLine.containsKey("paddle_speed")) {
            level.setPaddleSpeed(
                    Integer.parseInt(defMapLine.get("paddle_speed")));
        } else {
            levelWorks = false;
        }
        if (defMapLine.containsKey("level_name")) {
            level.setLevelName(defMapLine.get("level_name"));
        } else {
            levelWorks = false;
        }

        if (defMapLine.containsKey("num_blocks")) {
            level.setSumberOfBlocksToRemove(
                    Integer.parseInt(defMapLine.get("num_blocks")));
        } else {
            levelWorks = false;
        }
        if (defMapLine.containsKey("paddle_width")) {
            level.setPaddleWidth(
                    Integer.parseInt(defMapLine.get("paddle_width")));
        } else {
            levelWorks = false;
        }



        if (defMapLine.containsKey("ball_velocities")) {
            String[] velocities = defMapLine.get("ball_velocities")
                    .split("\\s+");
            for (int i = 0; i < velocities.length; i++) {
                String[] velocitiesCoords = velocities[i].split(",");
                Velocity vel = new Velocity(
                        Integer.parseInt(velocitiesCoords[0]),
                        Integer.parseInt(velocitiesCoords[1]));
                level.addBallVelocity(vel);
            }
            level.setNumberOfBalls(level.initialBallVelocities().size());
        } else {
            levelWorks = false;
        }

        if (defMapLine.containsKey("background")) {
            FillerMaker fillerMaker = new FillerMaker();
            Block backgroundBlock = new Block(
                    new Rectangle(new Point(30, 30), 740, 570));
            BlockFiller filler = fillerMaker
                    .fillerFromString(defMapLine.get("background"));
            backgroundBlock.setFiller(filler);
            level.setBackground(backgroundBlock);
        } else {
            levelWorks = false;
        }


        if ((defMapLine.containsKey("block_definitions"))
                && (defMapLine.containsKey("blocks_start_y"))
                && (defMapLine.containsKey("blocks_start_x"))
                && (defMapLine.containsKey("row_height"))) {
            int blockStartY = Integer
                    .parseInt(defMapLine.get("blocks_start_y"));
            int blockStartX = Integer
                    .parseInt(defMapLine.get("blocks_start_x"));
            int rowHigeht = Integer.parseInt(defMapLine.get("row_height"));
            BlocksFromSymbolsFactory blocksFactory = null;
            InputStreamReader in = null;

                try {
                    InputStream is = ClassLoader.getSystemClassLoader().
                            getResourceAsStream(defMapLine.get("block_definitions"));
                    in = new InputStreamReader(is);
                    blocksFactory = BlocksDefinitionReader.fromReader(in);
                } catch (Exception e) {
                    System.out.println("Can't find the file");
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            System.out.println(
                                    "IO problem");
                        }
                    }
                }

                if (blocksFactory != null) {
                    addBlocksToLevel(level, blocksFactory, blockDetail,
                            blockStartX, blockStartY, rowHigeht);
                } else {
                    levelWorks = false;
                }

            } else {
                levelWorks = false;
            }

            if (levelWorks) {
                return level;
            }
        return null;
    }


    /**
     * adds a block to level by the properties given.
     * @param level is the level that the block will be added to.
     * @param blockFromSymbols is where the block is produced.
     * @param blocksDefinition is the block lineDefinitions.
     * @param blockStartX is where the blocks row shows on the screen.
     * @param blockStartY is where the blocks row shows on the screen.
     * @param height is the height of the blocks row.
     */
    private static void addBlocksToLevel(LevelInformationFactory level,
                                         BlocksFromSymbolsFactory blockFromSymbols,
                                         List<String> blocksDefinition, int blockStartX, int blockStartY,
                                         int height) {
        int currentY = blockStartY;
        int ourX;

        for (String line : blocksDefinition) {
            ourX = blockStartX;
            for (int i = 0; i < line.length(); i++) {
                if (blockFromSymbols.isSpaceSymbol(line.substring(i, i + 1))) {
                    ourX += blockFromSymbols
                            .getSpaceWidth(line.substring(i, i + 1));
                } else if (blockFromSymbols.isBlockSymbol(line.substring(i, i + 1))) {
                    BlockInterface block = blockFromSymbols
                            .getBlock(line.substring(i, i + 1), ourX, currentY);
                    level.addBlock(block);
                    ourX += block.getCollisionRectangle().getWidth();
                }
            }
            currentY = currentY + height;
        }
    }
}