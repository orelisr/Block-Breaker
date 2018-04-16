
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author orel
 */
public class ColorParser {

    /**
     * Parses a color from a string.
     *
     * @param s - the string fo the color to parse.
     * @return The actual color object.
     */
    public Color colorFromString(String s) {
        if ((s.contains("rgb")) || (s.contains("RGB"))) {
            Pattern patt = Pattern.compile("\\d{1,3}");
            Matcher mt = patt.matcher(s);

            if (!mt.find()) {
                return null;
            }
            int r = Integer.parseInt(
                    s.substring(mt.start(), mt.end()));

            if (!mt.find()) {
                return null;
            }
            int g = Integer.parseInt(
                    s.substring(mt.start(), mt.end()));

            if (!mt.find()) {
                return null;
            }
            int b = Integer.parseInt(
                    s.substring(mt.start(), mt.end()));

            return new Color(r, g, b);
        } else if (s.contains("cyan")) {
            return Color.CYAN;
        } else if (s.contains("pink")) {
            return Color.PINK;
        } else if (s.contains("red")) {
            return Color.RED;
        } else if (s.contains("white")) {
            return Color.WHITE;
        } else if (s.contains("black")) {
            return Color.BLACK;
        } else if (s.contains("gray")) {
            return Color.GRAY;
        } else if (s.contains("lightGray")) {
            return Color.LIGHT_GRAY;
        } else if (s.contains("green")) {
            return Color.GREEN;
        } else if (s.contains("orange")) {
            return Color.ORANGE;
        } else if (s.contains("blue")) {
            return Color.BLUE;
        } else if (s.contains("yellow")) {
            return Color.YELLOW;
        }
        return null;
    }
}