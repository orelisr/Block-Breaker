
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author orel
 */
public class FillerMaker {


    /**
     * Gets the filler from the string.
     * @param s - to read from
     * @return the block filler decorator
     */
    public BlockFiller fillerFromString(String s) {
        if (s.contains("image")) {
            if (s.contains("(")) {
                s = s.substring(s.indexOf("(") + 1,
                        s.lastIndexOf(')'));
            }
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
                    Image image = ImageIO.read(is);
                    return new BlockFillImage(image);
            } catch (IOException e) {

                return new BlockFillColor(Color.WHITE);
            }

        } else {
            ColorParser color = new ColorParser();
            Color fillColor = color.colorFromString(s);
            return new BlockFillColor(fillColor);
        }
    }
}
