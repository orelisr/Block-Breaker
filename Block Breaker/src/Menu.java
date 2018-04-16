
/**
 * @author orel
 * @param <T> - the returned value
 */
public interface Menu<T> extends Animation {
    /**
     * adds a new selection.
     *
     * @param key - the key to press
     * @param message - the message to show
     * @param returnVal - return value
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * .
     * @return the current value of the menu
     */
    T getStatus();

    /**
     * Adds a new submenu.
     *
     * @param key - the key to press
     * @param message - the message to show
     * @param subMenu - the submenu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
