
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author orel
 */
public interface BlockInterface extends Collidable, Sprite, HitNotifier {

    /**
     * .
     * @return returns x text coordinates.
     */
    int getTextX();

    /**
     * .
     * @return returns y text coordinates.
     */
    int getTextY();

    /**
     * .
     * @return a copy of the block.
     */
    BlockInterface copy();

    /**
     * Sets a new y coordinate.
     * @param posY - new position.
     */
    void setNewYCord(int posY);

    /**
     * .
     * @return the amount of hits.
     */
    int getAmountOfHits();

    /**
     * .
     * @param hits - the new amount of hits.
     */
    void setAmountOfHits(int hits);

    /**
     * Sets a new color.
     * @param ourColor - the block's color
     */
    void setColor(Color ourColor);


    /**
     * Sets a new X coordinate.
     * @param posX - the x coordinate
     */
    void setXcoordinate(int posX);



    /**
     * Return the ball's new velocity.
     * @param hitter - the hitter ball
     * @param collisionPoint - the collission point
     * @param currentVelocity - the current velocity of the ball
     * @throws RuntimeException - in case of an exception
     * @return new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) throws RuntimeException;


    /**
     * draws the block on the screen.
     * @param d - our drawsurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify that time has passed.
     * @param dt - dt of the FPS
     */
    void timePassed(double dt);

    /**
     * Set a new Y coordinate.
     * @param posY - y coordinate
     */
    void setYcoordinate(int posY);

    /**
     * Sets a new X coordinate.
     * @param posX - our position x
     */
    void setNewXCord(int posX);


    /**
     * Set a new filler for the block.
     * @param blockFiller - our filler
     */
    void setFiller(BlockFiller blockFiller);

    /**
     * .
     * @return returns the collision rectangle.
     */
    Rectangle getCollisionRectangle();


    /**
     * Returns hit points of the block.
     * @return the hit points of the block.
     */
    int getHitPoints();

    /**
     * Restarts the amount of hits.
     */
    void restartHits();

    /**
     * Notifies in case of a hit.
     * @param hitter - ball hitter
     */
    void notifyHit(Ball hitter);


    /**
     * Adds the block to the game.
     * @param game current game
     */
    void addToGame(GameLevel game);


    /**
     * removes the block from the game.
     * @param game - our game
     */
    void removeFromGame(GameLevel game);

    /**
     * add a new hit listener to the list.
     * @param hl - a hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove the hit listener from the list.
     * @param hl - a hit listener
     */
    void removeHitListener(HitListener hl);

}