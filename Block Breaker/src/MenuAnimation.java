import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author orel
 * @param <T> - the value of the element
 */
public class MenuAnimation<T> implements Menu<T>, Animation {
    private List<Option<T>> selections;
    private boolean         stop;
    private Option<T>       currentOption;
    private String          title;
    private KeyboardSensor  ks;
    private Sprite          bg;



    @Override

    public void addSelection(String key, String s, T returnVal) {
        Option<T> newOption = new Option<T>(key, s, returnVal);
        this.selections.add(newOption);
    }

    @Override
    public T getStatus() {
        return this.currentOption.getValue();
    }

    /**
     * Constructor.
     *
     * @param t - title of the animation
     * @param keyboard - the keyboard
     * @param background - background of the animation
     */
    public MenuAnimation(String t, KeyboardSensor keyboard,
            Sprite background) {
        this.title = t;
        this.stop = false;
        this.ks = keyboard;
        this.currentOption = null;
        this.selections = new LinkedList<>();
        this.bg = background;
    }

    /**
     * Does one frame.
     *
     * @param d - the drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        int height = 0;
        this.bg.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(205, d.getHeight() / 2 - 55, this.title, 35);
        for (Option<T> option : this.selections) {
            d.drawText(201, d.getHeight() / 2 + height * 40,
                    "Press " + option.getKey() + " to " + option.getMessage(),
                    31);
            height++;
        }

        for (Option<T> theOption : this.selections) {
            if (this.ks.isPressed(theOption.getKey())) {
                this.currentOption = theOption;
                this.stop = true;
            }
        }
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.addSelection(key, message, subMenu.getStatus());
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void startOverAnimation() {
        this.stop = false;
    }


}