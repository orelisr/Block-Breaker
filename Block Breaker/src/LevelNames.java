
import biuoop.DrawSurface;
import java.awt.Color;

/**
* @author orel
*/
public class LevelNames implements Sprite {
    private Color  textColor;
    private String name;

    /**
     * Constructor.
     *
     * @param levelName - the level name.
     * @param color - color.
     */
    public LevelNames(String levelName, Color color) {
        this.name = levelName;
        this.textColor = color;
    }

    @Override
    public void timePassed(double dt) {
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.textColor);
        d.drawText(541, 21, "Level: " + this.name, 19);
    }

}