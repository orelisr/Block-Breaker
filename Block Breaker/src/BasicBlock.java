
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orel
 */
public abstract class BasicBlock implements Collidable, Sprite, HitNotifier,
        BlockInterface {

    private Rectangle rect;
    private BlockFiller filler;
    private int blockHits;
    private int textX;
    private int textY;
    private int originalHits;
    private List<HitListener> hitListeners;


    /**
     * Basic Block Constructor.
     *
     * @param rect - rectangle
     * @param col - color
     * @param numOfHits - amount of remaining hits
     */
    public BasicBlock(Rectangle rect, Color col, int numOfHits) {
        this.rect = new Rectangle(rect.getUpperLeft(), rect.getWidth(),
                rect.getHeight());
        this.filler = new BlockFillColor(col);
        this.blockHits = numOfHits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * BasicBlock Constructor.
     *
     * @param rect - The rectangle to be used for the block
     */
    public BasicBlock(Rectangle rect) {
        this.rect = new Rectangle(rect.getUpperLeft(), rect.getWidth(),
                rect.getHeight());
        this.filler = new BlockFillColor(Color.BLUE);
        this.originalHits = 0;
        this.blockHits = this.originalHits;
        this.textX = 0;
        this.textY = 0;
        this.hitListeners = new ArrayList<HitListener>();
    }


    /**
     * sets the text's X coordinates.
     *
     * @param posX - x position
     */
    public void setXcoordinate(int posX) {
        this.textX = posX;
    }

    /**
     * sets the text's X coordinates.
     *
     * @param posY - y position
     */
    public void setYcoordinate(int posY) {
        this.textY = posY;
    }


    @Override
    public void setNewXCord(int posX) {
        Point p = new Point(posX, this.rect.getUpperLeft().getY());
        this.rect = new Rectangle(p, this.rect.getWidth(), this.rect.getHeight());
    }

    @Override
    public void setNewYCord(int posY) {
        Point p = new Point(this.rect.getUpperLeft().getX(), posY);
        this.rect = new Rectangle(p, this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * returns the amount of hits.
     *
     * @return amount of hits.
     */
    public int getAmountOfHits() {
        return this.blockHits;
    }

    /**
     * sets the amount of hits.
     *
     * @param hits - amount of hits
     */
    public void setAmountOfHits(int hits) {
        this.originalHits = hits;
        this.blockHits = this.originalHits;
    }


    /**
     * Returns X coordinate of text.
     *
     * @return x
     */
    public int getTextX() {
        return this.textX;
    }

    /**
     * Returns Y coordinate of text.
     *
     * @return y
     */
    public int getTextY() {
        return this.textY;
    }



    /**
     * Remove the block from the game.
     *
     * @param game - our game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Returns the block filler.
     *
     * @return the filler
     */
    protected BlockFiller getFiller() {
        return this.filler;
    }

    /**
     * Sets a new blockfiller.
     * @param blockFiller the new blockfiller.
     */
    public void setFiller(BlockFiller blockFiller) {
        this.filler = blockFiller;
    }


    /**
     * Reduces the amount of hits.
     * @param hits - the amount of hits to reduce.
     */
    public void minusHits(int hits) {
        this.blockHits -= hits;
    }


    @Override
    /**
     * timepassed method.
     * @param dt (double) our dt of the FPS.
     */
    public void timePassed(double dt) {

    }

    /**
     * Adds the block to the game.
     *
     * @param game - our game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }


    /**
     * sets the color of the block.
     *
     * @param ourColor
     *            is the color of the block.
     */
    public void setColor(Color ourColor) {
        this.filler = new BlockFillColor(ourColor);
    }

    /**
     * returns the collission rectangle.
     *
     * @return Collission rectangle
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rect.getUpperLeft(),
                this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * Returns the velocity, abstract method.
     *
     * @param hitter - ball that hits
     * @param collisionPoint - our collission point.
     * @param currentVelocity - our current collission.
     * @return The new velocity
     * @throws RuntimeException - in case the collission isn't in the point
     */
    public abstract Velocity hit(Ball hitter, Point collisionPoint,
                                 Velocity currentVelocity) throws RuntimeException;

    /**
     * Draw on method.
     *
     * @param d - drawsurface
     */
    public void drawOn(DrawSurface d) {
        this.filler.drawOn(d, this.getCollisionRectangle());
    }



    /**
     * abstact, returns the amount of points.
     *
     * @return The amount of points.
     */
    public abstract int getHitPoints();

    /**
     * Restarts the amount of hits.
     */
    public void restartHits() {
        this.blockHits = this.originalHits;
    }

    @Override
    /**
     * Adds a new listener to our list.
     *
     * @param hl - the hit listener to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    /**
     * Removes a hit listener from our list.
     *
     * @param hl - hit listener to remove
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies the hit listeners that a new hit has occurred.
     *
     * @param hitter - the hitter block.
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
