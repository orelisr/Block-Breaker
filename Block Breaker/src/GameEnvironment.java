
import java.util.ArrayList;
import java.util.List;

/**
 * @author orel
 */
public class GameEnvironment {

    private List<Collidable> environmentList;

    /**
     * Constructor, initializes the array.
     */
    public GameEnvironment() {
        this.environmentList = new ArrayList<>();
    }



    /**
     * In case there is a collission with the trajectory with any of the items of the list
     * returns the collission info. Else, returns null.
     * @param trajectory - line to check with collission.
     * @return the collission.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo nfo = null;
        // Going through all the collidable in environmentList.
        for (int j = 0; j < this.environmentList.size(); j++) {
            Rectangle rect = ((Collidable) this.environmentList.get(j))
                    .getCollisionRectangle();
            Point p = trajectory
                    .closetIntersect(rect);

            if (p != null) {
                if (nfo == null) {
                    nfo = new CollisionInfo(p,
                            (Collidable) this.environmentList.get(j));
                } else if (trajectory.start()
                        .distance(p) < trajectory.start()
                        .distance(nfo.collisionPoint())) {
                    nfo = new CollisionInfo(p,
                            (Collidable) this.environmentList.get(j));
                }
            }
        }
        return nfo;
    }



    /**
     * Restars the environment.
     */
    public void restartGameEnvironment() {
        for (Collidable collidable : this.environmentList) {
            if  (collidable instanceof BlockInterface) {
                ((BlockInterface) collidable).restartHits();
            }
        }
    }

    /**
     *  Adds a new collidable.
     * @param c - the colliddable to add.
     */
    public void addCollidable(Collidable c) {
        this.environmentList.add(c);
    }

    /**
     * returns the collidables list.
     * @return the list.
     */
    public List<Collidable> getColidables() {
        return this.environmentList;
    }

    /**
     * Removes a collidable from the list.
     * @param c - collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environmentList.remove(c);
    }

}