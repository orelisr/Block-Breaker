
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orel
 */
public abstract class BlockDecorator implements BlockInterface {
    private BlockInterface blockDecoration;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     * @param block - our basic block.
     */
    public BlockDecorator(BlockInterface block) {
        this.blockDecoration = block;
        this.hitListeners = new ArrayList<HitListener>();
    }

    @Override
    /**
     * sets a new X coordinate.
     * @param posX - the new x coordinate.
     */
    public void setNewXCord(int posX) {
        this.blockDecoration.setNewXCord(posX);
    }

    @Override
    /**
     * sets a new Y coordinate.
     * @param posY - the new Y coordinate.
     */
    public void setNewYCord(int posY) {
        this.blockDecoration.setNewYCord(posY);
    }

    /**
     * Returns the block after the decoration.
     * @return the decorated block
     */
    protected BlockInterface getDecoratedBlock() {
        return this.blockDecoration;
    }


    @Override
    /**
     * sets the amount of hits.
     * @param hits - the amount of hits
     */
    public void setAmountOfHits(int hits) {
        this.getDecoratedBlock().setAmountOfHits(hits);
    }

    @Override
    /**
     * sets the color of the block.
     * @param ourColor is the color of the block.
     */
    public void setColor(Color ourColor) {
        this.getDecoratedBlock().setColor(ourColor);
    }


    @Override
    /**
     * Gets the x coordinate of the text.
     * @return - x coordinate of text
     */
    public int getTextX() {
        return this.getDecoratedBlock().getTextX();
    }

    @Override
    /**
     * Get the y coordinate of the text.
     * @return - y ccordinate of the text.
     */
    public int getTextY() {
        return this.getDecoratedBlock().getTextY();
    }

    @Override
    /**
     * Set the x coordinate of the text.
     * @param posX - position of the x
     */
    public void setXcoordinate(int posX) {
        this.getDecoratedBlock().setXcoordinate(posX);
    }

    @Override
    /**
     * Set the y coordinate of the text.
     * @param posY - position of the y
     */
    public void setYcoordinate(int posY) {
        this.getDecoratedBlock().setYcoordinate(posY);
    }


    @Override
    /**
     * Returns the velocity of the ball after the hit.
     * @param hitter - the hitting ball.
     * @param collisionPoint - the collission point.
     * @param currentVelocity - current ball velocity.
     * @return the velocity of the ball
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Velocity velocityBefore = hitter.getVelocity();
        Velocity velocityAfter = this.getDecoratedBlock().hit(hitter, collisionPoint, currentVelocity);
        if (!((velocityBefore.getDx() == velocityAfter.getDx())
                && (velocityBefore.getDy() == velocityAfter.getDy()))) {
            this.notifyHit(hitter);
        }
        return velocityAfter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.getDecoratedBlock().drawOn(d);
    }

    @Override
    public void timePassed(double dt) {
        this.getDecoratedBlock().timePassed(dt);
    }




    @Override
    public int getAmountOfHits() {
        return this.getDecoratedBlock().getAmountOfHits();
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notifies the hit lesteners that the ball hits.
     * @param hitter ball
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    /**
     * sets the filler of the block.
     * @param blockFiller is the block filler.
     */
    public void setFiller(BlockFiller blockFiller) {
        this.blockDecoration.setFiller(blockFiller);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.getDecoratedBlock().getCollisionRectangle();
    }


    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }


    @Override
    public int getHitPoints() {
        return this.getDecoratedBlock().getHitPoints();
    }

    @Override
    public void restartHits() {
        this.blockDecoration.restartHits();
    }
}