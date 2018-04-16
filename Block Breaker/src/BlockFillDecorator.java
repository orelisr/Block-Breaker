
import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * @author orel
 */
public class BlockFillDecorator extends BlockDecorator {
    private ArrayList<BlockFiller> listOfFillers;



    /**
     * Draws on the gui.
     *
     * @param d - drawsurface.
     */
    public void drawOn(DrawSurface d) {
        super.drawOn(d);

        // Draw the block with the matching fill.
        if (this.getAmountOfHits() > this.listOfFillers.size()) {
            this.listOfFillers.get(this.listOfFillers.size() - 1).drawOn(d,
                    this.getCollisionRectangle());
        } else {
            this.listOfFillers.get(this.getAmountOfHits() - 1).drawOn(d,
                    this.getCollisionRectangle());
        }
    }

    /**
     * Constructor of the block.
     *
     * @param block - our block
     * @param fillers - the filler that need to be used.
     */
    public BlockFillDecorator(BlockInterface block,
            ArrayList<BlockFiller> fillers) {
        super(block);
        this.listOfFillers = fillers;
    }

    @Override
    public BlockInterface copy() {
        return new BlockFillDecorator(this.getDecoratedBlock().copy(),
                this.listOfFillers);
    }

}