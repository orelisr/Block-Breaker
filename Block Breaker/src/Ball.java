
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author orel
 */
public class Ball implements Sprite {

    private Point center;
    private int radios;
    private Color  color;
    private Point destPoint;
    private Point upperPoint;
    private Velocity vel;
    private Rectangle borders;
    private Point bottomPoint;
    private Point destinationUp;
    private Point destinationBottom;
    private GameEnvironment game;


    /**
     * new Ball constructor.
     *
     * @param center - center of the ball
     * @param r - balls radius
     * @param color - Color
     */
    public Ball(Point center, int r, Color color) {
        this((int) center.getX(), (int) center.getY(), r, color);
    }

    /**
     * new Ball constructor.
     *
     * @param x - x of the center of the ball
     * @param y - y of the center of the ball
     * @param r - radius of the ball
     * @param color - ball's color
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radios = r;
        this.color = color;
        this.borders = null;
        this.upperPoint = null;
        this.bottomPoint = null;
        this.destinationUp = null;
        this.vel = null;
        this.game = null;
        this.destinationBottom = null;
        this.destPoint = null;
    }


    /**
     * returns the center of the ball.
     *
     * @return the center of the ball.
     */
    public Point getCenter() {
        return new Point(this.center.getX(), this.center.getY());
    }


    /**
     * Sets a new ball velocity.
     *
     * @param velnew - new velocity
     */
    public void setVelocity(Velocity velnew) {
        this.vel = velnew;
        this.createPoints();
        this.destination();
    }


    /**
     * return x coordinate of the ball.
     *
     * @return x coordinate (int)
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * return y coordinate of the ball.
     *
     * @return y coordinate (int)
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * get balls radius.
     *
     * @return radius (int)
     */
    public int getSize() {
        return this.radios;
    }

    /**
     * return ball's color.
     *
     * @return color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * sets the balls color.
     *
     * @param ourColor - new color
     */
    public void setColor(Color ourColor) {
        this.color = ourColor;
    }

    /**
     * returns ball's velocity.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.vel;
    }


    /**
     * sets the area of the ball.
     *
     * @param topLeft - top left point
     * @param bottomRight - bottom right point
     * @return true if the area set was succesful, false if not
     */
    public boolean setArea(Point topLeft, Point bottomRight) {
        // If 'topLeft' Point, is at the top left side of 'bottomRight' Point.
        if ((topLeft.getX() < bottomRight.getX())
                && (topLeft.getY() < bottomRight.getY())) {
            this.setArea(new Point(topLeft.getX(), topLeft.getY()),
                    bottomRight.getX() - topLeft.getX(),
                    bottomRight.getY() - topLeft.getY());
            return true;
        }
        return false;
    }

    /**
     * set the area of the ball.
     *
     * @param topLeft - top left point
     * @param width - area width
     * @param height - area height
     */
    public void setArea(Point topLeft, double width, double height) {
        this.borders = new Rectangle(new Point(topLeft.getX(), topLeft.getY()),
                width, height);
        this.destination();
    }


    /**
     * checks if the ball arrives to any of it's borders. Uses line equation.
     * checks for line intersection
     */
    private void destination() {
        if (this.borders != null && this.vel != null) {
            double m = this.vel.getDy() / this.vel.getDx();
            double b = this.upperPoint.getY()
                    - this.upperPoint.getX() * m;
            Point horizonal, vertical;
            double y, x;
            if (this.vel.getDy() < 0) {
                y = this.borders.getUpperLeft().getY();
            } else {
                y = this.borders.getBottomLeft().getY();
            }
            x = (y - b) / m;
            vertical = new Point(x, y);
            if (this.vel.getDx() > 0) {
                x = this.borders.getUpperRight().getX();
            } else {
                x = this.borders.getUpperLeft().getX();
            }
            y = m * x + b;
            horizonal = new Point(x, y);

            this.destinationUp = this.upperPoint.closerDistance(vertical,
                    horizonal);

            m = this.vel.getDy() / this.vel.getDx();
            b = this.bottomPoint.getY()
                    - this.bottomPoint.getX() * m;

            if (this.vel.getDy() < 0) {
                y = this.borders.getUpperLeft().getY();
            } else {
                y = this.borders.getBottomLeft().getY();
            }
            x = (y - b) / m;
            vertical = new Point(x, y);

            if (this.vel.getDx() > 0) {
                x = this.borders.getUpperRight().getX();
            } else {
                x = this.borders.getUpperLeft().getX();
            }
            y = m * x + b;
            horizonal = new Point(x, y);

            this.destinationBottom = this.bottomPoint.closerDistance(vertical,
                    horizonal);

            m = this.vel.getDy() / this.vel.getDx();
            b = this.getY() - this.getX() * m;
            if (this.vel.getDy() < 0) {
                y = this.borders.getUpperLeft().getY();
            } else {
                y = this.borders.getBottomLeft().getY();
            }
            x = (y - b) / m;
            vertical = new Point(x, y);

            if (this.vel.getDx() > 0) {
                x = this.borders.getUpperRight().getX();
            } else {
                x = this.borders.getUpperLeft().getX();
            }
            y = m * x + b;
            horizonal = new Point(x, y);

            this.destPoint = this.center.closerDistance(horizonal, vertical);
        }
    }

    /**
     * draws the ball on the screen.
     * @param d - drawsurface to draw on
     * @override
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle(this.getX(), this.getY(), this.getSize());
        d.setColor(Color.BLACK);
        d.drawCircle(this.getX(), this.getY(), this.getSize());
    }


    /**
     * sets game environment.
     *
     * @param ourGame - game to setup the game environment.
     */
    public void setGameEnvironment(GameEnvironment ourGame) {
        this.game = ourGame;
    }

    /**
     * calculates the balls upper and lower points every time.
     * Using its velocity.
     */
    private void createPoints() {
        Velocity verticalVel = new Velocity(this.getVelocity().getDy(),
                -this.getVelocity().getDx());
        double t = Math.pow(verticalVel.getDy(), 2)
                + Math.pow(verticalVel.getDx(), 2);
        t = Math.sqrt(t);
        t = this.getSize() / t;
        this.upperPoint = new Point(this.getX() + t * verticalVel.getDx(),
                this.getY() + t * verticalVel.getDy());
        this.bottomPoint = new Point(this.getX() - t * verticalVel.getDx(),
                this.getY() - t * verticalVel.getDy());
    }



    /**
     * adds the ball to the game.
     *
     * @param ourGame - game to add the ball to.
     */
    public void addToGame(GameLevel ourGame) {
        ourGame.addSprite(this);
    }



    /**
     * removes the ball sprite from the game.
     *
     * @param ourGame - the game
     */
    public void removeFromGame(GameLevel ourGame) {
        ourGame.removeSprite(this);
    }


    /**
     * moves the ball forward, checks for positions and makes sure it doesn't hit anything
     * if a hit occurs, changes the velocity.
     * @param dt - time passed from last time according to the GPS
     */
    public void moveOneStep(double dt) {
        if ((this.vel != null) && (this.borders != null)
                && (this.game != null)) {
            this.createPoints();
            Point tempU, tempB, tempDest;
            CollisionInfo colUp, colDown, col;

            colUp = this.game.getClosestCollision(
                    new Line(this.upperPoint, this.destinationUp));
            colDown = this.game.getClosestCollision(
                    new Line(this.bottomPoint, this.destinationBottom));
            col = this.game
                    .getClosestCollision(new Line(this.center, this.destPoint));

            if (colUp != null) {
                tempU = colUp.collisionPoint();
            } else {
                tempU = new Point(this.destinationUp.getX(),
                        this.destinationUp.getY());
            }

            if (colDown != null) {
                tempB = colDown.collisionPoint();
            } else {
                tempB = new Point(this.destinationBottom.getX(),
                        this.destinationBottom.getY());
            }

            // Checks the closest intersection of this.bottomPoint
            if (col != null) {
                tempDest = col.collisionPoint();
            } else {
                tempDest = new Point(this.getX(), this.getY());
            }

            Point destinationNow = this.center.closerDistance(tempB,
                    tempU);
            destinationNow = this.center.closerDistance(destinationNow,
                    tempDest);

            CollisionInfo currentCollissionP;

            if (destinationNow.equals(tempU)) {
                currentCollissionP = colUp;
            } else if (destinationNow.equals(tempB)) {
                currentCollissionP = colDown;
            } else {
                currentCollissionP = col;
            }

            Point afterStep = this.getVelocity().applyToPoint(this.center, dt);

            // checks for collision after
            if (afterStep.distance(destinationNow) < this.radios) {
                this.setVelocity(currentCollissionP.collisionObject().hit(this,
                        destinationNow, this.getVelocity()));
                this.center = this.getVelocity().applyToPoint(this.center, dt);
            } else {
                this.center = this.getVelocity().applyToPoint(this.center, dt);
            }
        }
    }

    /**
     * notifies that ball that time has passed.
     * @param dt - dt of FPS
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }


}