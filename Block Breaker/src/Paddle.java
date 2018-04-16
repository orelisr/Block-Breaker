
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author orel
 */
public class Paddle implements Sprite, Collidable {


    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private double borderR;
    private double borderL;
    private int paddleSpeed;

    /**
     * Constructor.
     *
     * @param rect - the paddle' rectangle
     * @param speed - the paddle's speed
     * @param borderL - the left border
     * @param borderR - the right border
     * @param gui - the game GUI
     * @param paddleColor - color
     */
    public Paddle(Rectangle rect, int speed, double borderL,
            double borderR, GUI gui, Color paddleColor) {
        this.keyboard = gui.getKeyboardSensor();
        this.paddleSpeed = speed;
        Point p = rect.getUpperLeft();
        this.rect = new Rectangle(p , rect.getWidth(), rect.getHeight());
        this.color = paddleColor;
        //setting the area of the paddle
        checkBorders(borderL, borderR);
    }

    /**
     * checks which of the borders is bigger.
     * @param left - left border
     * @param right - right border
     */
    public void checkBorders(double left, double right) {

        if (left < right) {
            this.setArea(left, right);
        } else {
            this.setArea(right, left);
        }
    }

    /**
     * .
     * @return returns the right border
     */
    private double getRightBorder() {
        return this.borderR;
    }

    /**
     * .
     * @return returns the left border
     */
    private double getLeftBorder() {
        return this.borderL;
    }


    /**
     * Sets the color of the paddle.
     * @param c - color
     */
    public void setColor(Color c) {
        this.color = c;
    }


    /**
     * Moves the paddle to the left according to DT and paddle's speed.
     * @param dt - time according to FPS
     */
    public void moveLeft(double dt) {
        Rectangle rectangle = this.getCollisionRectangle();
        Point left = new Point(rectangle.getUpperLeft().getX()
                        - this.paddleSpeed * dt, rectangle.getUpperLeft().getY());

        if (this.getLeftBorder() > left.getX()) {
            left = new Point(this.getLeftBorder(), rectangle.getUpperLeft().getY());
        }

        this.rect = new Rectangle(left, rectangle.getWidth(), rectangle.getHeight());
    }


    /**
     * sets the area of the paddle.
     *
     * @param left - left
     * @param right - right
     */
    private void setArea(double left, double right) {
        this.borderL = left;
        this.borderR = right;
    }

    /**
     * .
     * @return gets the color
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * Moves the paddle to the right according to DT and paddle's speed.
     * @param dt - time according to FPS
     */
    public void moveRight(double dt) {
        Rectangle rectangle = this.getCollisionRectangle();
        Point right = new Point(rectangle.getUpperLeft().getX()
                        + this.paddleSpeed * dt, rectangle.getUpperLeft().getY());

        if (right.getX() > this.borderR) {
            right = new Point(this.getRightBorder(), rectangle.getUpperLeft().getY());
        }

        this.rect = new Rectangle(right, rectangle.getWidth(), rectangle.getHeight());
    }


    /**
     * notifies the paddle that the time has passed.
     *
     * @param dt - dt of the FPS
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        } else if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
    }


    /**
     * Move the paddle to a new location.
     *
     * @param x - the new position
     */
    public void movePaddleToX(int x) {
        Rectangle rectangle = this.getCollisionRectangle();
        if (x > this.borderL && x < this.borderR) {
            Point newP = new Point(x, rectangle.getUpperLeft().getY());
            this.rect = new Rectangle(newP, rectangle.getWidth(), rectangle.getHeight());
        }
    }

    /**
     * .
     * @return returns the rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rect.getUpperLeft(), this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * When a hit occured with the paddle.
     *
     * @param hitter - hitter ball
     * @param collisionPoint - where the collission occured
     * @param vel - the velocity of the ball
     * @return the ball's new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity vel) {
        Rectangle rectangle = this.getCollisionRectangle();
        boolean upCol, downCol, leftCol,
                rightCol;

        downCol = (collisionPoint.isBetween(rectangle.getBottomLeft(), rectangle.getBottomRight()))
                && (collisionPoint.isBetweenY(rectangle.getBottomLeft(), rectangle.getBottomRight()));
        upCol = collisionPoint.isBetween(rectangle.getUpperLeft(), rectangle.getUpperRight())
                && (collisionPoint.isBetweenY(rectangle.getUpperLeft(), rectangle.getUpperRight()));
        rightCol = (collisionPoint.isBetween(rectangle.getUpperRight(), rectangle.getBottomRight()))
                && (collisionPoint.isBetweenY(rectangle.getUpperRight(), rectangle.getBottomRight()));
        leftCol = (collisionPoint.isBetween(rectangle.getUpperLeft(), rectangle.getBottomLeft()))
                && (collisionPoint.isBetweenY(rectangle.getUpperLeft(), rectangle.getBottomLeft()));
        if (downCol || upCol) {
            double paddleCollission = collisionPoint.getX()
                    - rectangle.getUpperLeft().getX();
            double width = rectangle.getWidth();
            if (width / 5 >= paddleCollission) {
                return Velocity.fromAngleAndSpeed(10, -301);
            } else if ((width / 5) * 3 <= paddleCollission) {
                return Velocity.fromAngleAndSpeed(140, 201);
            } else if ((width / 5) * 4 <= paddleCollission) {
                return Velocity.fromAngleAndSpeed(170, 301);
            } else if ((width / 5) * 2 >= paddleCollission) {
                return Velocity.fromAngleAndSpeed(40, -201);
            } else {
                if (vel.getDy() == 0 || vel.getDx() == 0) {
                    return new Velocity(-vel.getDx(),
                            -vel.getDy());
                } else {
                    return new Velocity(vel.getDx(),
                            -vel.getDy());
                }
            }
        } else if (leftCol) {
            return Velocity.fromAngleAndSpeed(10, -40);
        } else if (rightCol) {
            return Velocity.fromAngleAndSpeed(170, 40);
        }
        return new Velocity(vel.getDx(), -vel.getDy());
    }


    /**
     * Draws the paddle.
     * @param d - the drawsurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Rectangle rectangle = this.getCollisionRectangle();
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }


    /**
     * adds the paddle to the game.
     *
     * @param g - our game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}