
/**
 * @author orel
 */
public interface Collidable {
    /**
     * Returns the collission rectangle.
     * @return the rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that has been hit.
     * @param hitter - hitter ball
     * @param collisionPoint - collission point
     * @param currentVelocity - velocity of the ball
     * @return the new Velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}