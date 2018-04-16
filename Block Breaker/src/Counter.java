
/**
 * @author orel
 */
public class Counter {
    private int counter;

    /**
     * Constructor.
     * @param c - starting counter
     * */
    public Counter(int c) {
        this.counter = c;
    }

    /**
     * decreases the counter.
     * @param d - by how much.
     */
    public void decrease(int d) {
        this.counter = this.counter - d;
        }


    /**
     * increases the counter.
     * @param i - by how much.
     */
    public void increase(int i) {
        this.counter = this.counter + i;
        }

    /**
     * Returns the counters value.
     * @return the counters value
     */
    public int getValue() {
        return this.counter;
        }
}