
/**
 * @author orel
 */
public class CollisionInfo {

    private Collidable collisionObject;
    private Point collisionPoint;


    /**
     * Constructor.
     *
     * @param colP - collission Point
     * @param colO - collission object
     */
    public CollisionInfo(Point colP, Collidable colO) {
        this.collisionPoint = colP;
        this.collisionObject = colO;
    }


    /**
     * .
     *
     * @return Returns the collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }


    /**
     * .
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint.getX(),
                this.collisionPoint.getY());
    }

}