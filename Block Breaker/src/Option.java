
/**
 * @author orel
 * @param <T> - optionValue of the option
 */
public class Option<T> {
    private String optionKey;
    private T optionValue;
    private String text;

    /**
     * Constructor.
     *
     * @param key - key to be pressed
     * @param s - the text
     * @param val - return value
     */
    public Option(String key, String s, T val) {
        this.optionKey = key;
        this.optionValue = val;
        this.text = s;
    }


    /**
     * .
     * @return returns the value
     */
    public T getValue() {
        return this.optionValue;
    }


    /**
     * .
     * @return the key.
     */
    public String getKey() {
        return this.optionKey;
    }

    /**
     * .
     * @return gets the message
     */
    public String getMessage() {
        return this.text;
    }
}