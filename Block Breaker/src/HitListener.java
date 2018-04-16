
/**
 * @author orel
 */
public  interface HitListener {
    /** Calls the function in case a hit occured.
     *
     * @param beingHit - block that was being hit
     * @param hitter - ball that hits.
     */
    void hitEvent(BlockInterface beingHit, Ball hitter);
 }