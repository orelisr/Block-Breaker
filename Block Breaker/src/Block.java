

/**
 * @author orel
 */
public class Block extends BasicBlock
        implements Collidable, Sprite, HitNotifier {
    /**
     * Constructor of a new block.
     *
     * @param rect - our block's rectangle.
     */
    public Block(Rectangle rect) {
        super(rect);
    }


    /**
     * Gets the amount of hit points.
     *
     * @return points earned
     */
    public int getHitPoints() {
        if (this.getAmountOfHits() == 0) {
            return 15;
        } else {
            return 5;
        }
    }

    @Override
    /**
     * A copy method. Returns a copy of the block.
     * @return A copy
     */
    public BlockInterface copy() {
        Block block = new Block(this.getCollisionRectangle().copy());
        block.setFiller(this.getFiller());
        block.setAmountOfHits(this.getAmountOfHits());
        return block;
    }

    /**
     * Retures the velocity after the collission occured, using the collission info and ball's
     * earlier velocity.
     *
     * @param hitter - our hitting ball.
     * @param collisionPoint - our collission point
     * @param currentVelocity - our current velocity
     * @return Our new velocity.
     * @throws RuntimeException - in case the collission occured outside of the block
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) throws RuntimeException {
        Velocity vel;
        boolean topCollision, bottomCollision, leftCollision,
                rightCollision;

        topCollision = collisionPoint.isBetween(
                this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getUpperRight())
                && (collisionPoint.isBetweenY(
                this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getUpperRight()));
        bottomCollision = (collisionPoint.isBetween(
                this.getCollisionRectangle().getBottomLeft(),
                this.getCollisionRectangle().getBottomRight()))
                && (collisionPoint.isBetweenY(
                this.getCollisionRectangle().getBottomLeft(),
                this.getCollisionRectangle().getBottomRight()));

        rightCollision = (collisionPoint.isBetween(
                this.getCollisionRectangle().getUpperRight(),
                this.getCollisionRectangle().getBottomRight()))
                && (collisionPoint.isBetweenY(
                this.getCollisionRectangle().getUpperRight(),
                this.getCollisionRectangle().getBottomRight()));

        leftCollision = (collisionPoint.isBetween(
                this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getBottomLeft()))
                && (collisionPoint.isBetweenY(
                this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getBottomLeft()));

        //in case a collission happened in any of the areas
        if ((topCollision && leftCollision)
                || (topCollision && rightCollision)
                || (bottomCollision && leftCollision)
                || (bottomCollision && rightCollision)) {
            vel = new Velocity(-1 * currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());

        } else if (topCollision) {
            vel = new Velocity(currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());

        } else if (bottomCollision) {
            vel = new Velocity(currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());

        } else if (leftCollision) {
            vel = new Velocity(-1 * currentVelocity.getDx(),
                    currentVelocity.getDy());

        } else if (rightCollision) {
            vel = new Velocity(-1 * currentVelocity.getDx(),
                    currentVelocity.getDy());
        } else {

            throw new RuntimeException("Our collission caused an error");
        }

        if (this.getAmountOfHits() != 0) {
            this.minusHits(1);
        }

        if (currentVelocity.getDy() == 0 || currentVelocity.getDx() == 0) {
            vel = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }

        this.notifyHit(hitter);
        return vel;
    }

}