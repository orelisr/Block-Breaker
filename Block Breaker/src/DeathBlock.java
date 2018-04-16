

/**
 * @author orel
 */
public class DeathBlock extends BasicBlock
        implements Collidable, Sprite, HitNotifier {

    /**
     * Constructor.
     *
     * @param rect - rectangle of the death block.
     */
    public DeathBlock(Rectangle rect) {
        super(rect);
    }

    @Override
    public BlockInterface copy() {
        DeathBlock block = new DeathBlock(this.getCollisionRectangle().copy());
        block.setFiller(this.getFiller());

        int x;
        return block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) throws RuntimeException {
        this.notifyHit(hitter);
        return new Velocity(1, 1);
    }

    @Override
    public int getHitPoints() {
        return 0;
    }
}