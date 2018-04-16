
/**
 * @author orel
 */
public interface HitNotifier {
    /**
     * Add a new hitlistener.
     * @param hl - the hit listener to add
     */
    void addHitListener(HitListener hl);


    /**
     * Removes a hit listener.
     * @param hl - the one to remove.
     */
    void removeHitListener(HitListener hl);
 }