
import biuoop.DrawSurface;
import java.awt.Image;

/**
 * @author orel
 */
public class BlockFillImage implements BlockFiller {
    private Image blockImage;

    /**
     * fill the block with image.
     *
     * @param imageToFill - Our image to fill the block with
     */
    public BlockFillImage(Image imageToFill) {
        this.blockImage = imageToFill;
    }



    @Override
    public void drawOn(DrawSurface d, Rectangle rect) {
        d.drawImage((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), this.blockImage);
    }
}