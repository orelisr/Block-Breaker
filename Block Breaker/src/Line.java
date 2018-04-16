
import java.util.List;

/**
 * @author orel
 */
public class Line {

    private Point start;
    private Point end;
    private double m;

    /**
     * Constructor.
     *
     * @param start - the start of the line
     * @param end - the end of the line
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * Constructor.
     *
     * @param x1 - x of the start
     * @param y1 - y of the start
     * @param x2 - x of the end
     * @param y2 - y of the end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point((int) x1, (int) y1);
        this.end = new Point((int) x2, (int) y2);


        if (((int) x1 == (int) x2) || ((int) y1 == (int) y2)) {
            this.m = 0;
        } else {
            this.m = ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
        }
    }


    /**
     * .
     * @return the end point
     */
    public Point end() {
        return this.end;
    }


    /**
     * checks of the lines are intersecting.
     *
     * @param second - second line
     * @return true if intersecting, false if not
     */
    public boolean isIntersecting(Line second) {
        if (this.intersectionWith(second) == null) {
            return false;
        }
        return true;
    }


    /**
     * .
     * @return the start point
     */
    public Point start() {
        return this.start;
    }


    /**
     * If the first line is horizonal and second is vertical.
     *
     * @param vertical - vertical line
     * @return - point of intersection
     */
    private Point firstHorizonalSecondVertical(Line vertical) {
        Point p = new Point(vertical.start().getX(),
                this.start.getY());
        if (p.isBetween(this.start, this.end) && p
                .isBetweenY(vertical.start(), vertical.end())) {
            return p;
        }
        return null;
    }

    /**
     * Returns the point in case the firs is a line and second is a point.
     *
     * @param second - the second line
     * @return if the intersection occurs returns it, secondwise returns null
     */
    private Point firstLineSecondPoint(Point second) {
        if (this.horizonal()) {
            if (second.getY() == this.start.getY()
                    && second.isBetween(this.start, this.end)) {
                Point p = new Point(second.getX(), second.getY());
                return p;
            }
            return null;
        } else if (second.getX() == this.start.getX()
                && second.isBetweenY(this.start, this.end)) {
            Point p = new Point(second.getX(), second.getY());
            return p;
        }
        return null;
    }


    /**
     * Returns the point between in case both lines are horizonal.
     *
     * @param second - second line
     * @return the intersection point
     */
    private Point horizonalPair(Line second) {
        if (!this.start.sameLineHorizonal(second.start())) {
            return null;
        } else {
            return this.bothInfinity(second);
        }
    }

    /**
     * In case both lines are vertical.
     *
     * @param second - second line
     * @return the intersection
     */
    private Point verticalPair(Line second) {

        if (!this.start.sameLineVertical(second.start())) {

            return null;
        } else {
            Point endLine, startLine = null;


            if (this.end().isBetweenY(second.start(), second.end())) {
                if (startLine == null) {
                    startLine = this.end;
                } else {
                    endLine = this.end;
                    Line connectLine = new Line(startLine,
                            endLine);
                    return this.start.closerDistance(connectLine.start, connectLine.end);
                }
            }
            if (this.start.isBetweenY(second.start(), second.end())) {
                startLine = this.start;
            }

            if (startLine != null) {
                endLine = second.end();
                Line connectLine = new Line(startLine, endLine);
                return this.start.closerDistance(connectLine.start, connectLine.end);
            }

            if (second.start().isBetweenY(this.start, this.end)) {
                if (startLine == null) {
                    ;
                } else {
                    endLine = second.start();
                    Line connectLine = new Line(startLine,
                            endLine);
                    return this.start.closerDistance(connectLine.start, connectLine.end);
                }
            }

            return null;
        }
    }


    /**
     * If both of them have infinitity incline.
     *
     * @param second - the second point
     * @return the point of intersection
     */
    private Point bothInfinity(Line second) {
        Point endLine, startLine = null;

        if (this.start.isBetween(second.start(), second.end())) {
            startLine = this.start;
        }

        if (this.end().isBetween(second.start(), second.end())) {
            if (startLine == null) {
                startLine = this.end;
            } else {
                endLine = this.end;
                Line connectLine = new Line(startLine, endLine);
                return this.start.closerDistance(connectLine.start, connectLine.end);
            }
        }



        if (startLine != null) {
            endLine = second.end();
            Line connectLine = new Line(startLine, endLine);
            return this.start.closerDistance(connectLine.start, connectLine.end);
        }

        if (second.start().isBetween(this.start, this.end)) {
            if (startLine == null) {
                startLine = second.start();
            } else {
                endLine = second.start();
                Line connectLine = new Line(startLine, endLine);
                return this.start.closerDistance(connectLine.start, connectLine.end);
            }
        }

        return null;
    }

    /**
     * In case the first is regular, the second isn't.
     *
     * @param unRegular - if the second isn't normal
     * @return (Point) If the lines intersects, the method returns the
     *         intersection point of the lines, secondwise, the method returns
     *         null.
     */
    private Point firstNormalSecondOne(Line unRegular) {
        if (unRegular.horizonal()) {
            double a = this.m;
            double b = (this.start.getY() - this.start.getX() * a);
            double y = unRegular.start.getY();
            double x = (y - b) / a;
            Point equationPoint = new Point(x, y);
            if ((equationPoint.isBetween(this.start, this.end)) && (equationPoint
                    .isBetween(unRegular.start, unRegular.end))) {
                return equationPoint;
            } else {
                return null;
            }
        } else if (unRegular.vertical()) {
            double a = this.m;
            double b = (this.start.getY() - this.start.getX() * a);
            double xVertical = unRegular.start.getX();
            double y = a * xVertical + b;
            Point equationPoint = new Point(xVertical, y);
            if ((equationPoint.isBetween(this.start, this.end)) && (equationPoint
                    .isBetweenY(unRegular.start, unRegular.end))) {
                return equationPoint;
            } else {
                return null;
            }
        } else {
            double a = this.m;
            double b = (this.start.getY() - this.start.getX() * a);

            double newY = a * unRegular.start().getX() + b;

            if (newY != unRegular.start().getY()) {
                return null;
            }

            Point p = new Point(unRegular.start().getX(),
                    unRegular.start().getY());
            if (p.isBetween(this.start, this.end)) {
                return p;
            }

            return null;
        }
    }

    /**
     * Returns the point of intersection.
     *
     * @param second - second line
     * @return If intersect, returns point, else returns null
     */
    public Point intersectionWith(Line second) {
        if (this.m == 0 && second.m == 0) {
            if (this.horizonal() && second.horizonal()) {
                return this.horizonalPair(second);
            } else if (this.start.equals(this.end)) {
                return second.firstLineSecondPoint(this.start);
            }  else if (this.vertical() && second.horizonal()) {
                return second.firstHorizonalSecondVertical(this);
            }  else if (this.horizonal() && second.vertical()) {
                return this.firstHorizonalSecondVertical(second);
            } else if (this.vertical() && second.vertical()) {
                return this.verticalPair(second);
            } else {
                return this.firstLineSecondPoint(second.start());
            }

        } else if (this.m != 0 && second.m != 0) {

            double a = this.m;
            double aSecond = second.m;
            double b = (this.start.getY() - this.start.getX() * a);
            double bSecond = (second.start.getY() - second.start.getX() * aSecond);

            if (a == aSecond) {
                if (b != bSecond) {
                    return null;
                }
                return this.bothInfinity(second);
            }

            double x = (bSecond - b) / (a - aSecond);
            double y = a * x + b;
            Point equationPoint = new Point(x, y);
            if ((equationPoint.isBetween(this.start, this.end))
                    && (equationPoint.isBetween(second.start, second.end))) {
                return equationPoint;
            } else {
                return null;
            }

        } else {
            if ((this.m == 0) && (second.m != 0)) {
                return second.firstNormalSecondOne(this);
            } else {
                return this.firstNormalSecondOne(second);
            }
        }
    }

    /**
     * .
     * @return true or false if the line is horizonal.
     */
    public boolean horizonal() {
        if (this.start.equals(this.end)) {
            return false;
        }
        return this.start.sameLineHorizonal(this.end);
    }

    /**
     * .
     * @return Returns if the line is vertical
     */
    public boolean vertical() {
        if (this.start.equals(this.end)) {
            return false;
        }
        return this.start.sameLineVertical(this.end);
    }



    /**
     * Finds the closet intersection to the start of the line.
     * @param rect - the rectangle
     * @return closest intersection
     */
    public Point closetIntersect(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.size() == 0) {
            return null;
        } else {
            Point p = intersections.get(0);
            for (int i = 0; i < intersections.size(); i++) {
                Point check = intersections.get(i);
                if (check.distance(this.start()) < p.distance(this.start())) {
                    p = intersections.get(i);
                }
            }
            return p;
        }
    }
}