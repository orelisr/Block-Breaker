
/**
 * @author orel
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructor.
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * checks if the y is on the same y as the other point.
     * @param other - a point to check
     * @return if the y is the same, return true. otherwise return false.
     */
    public boolean sameLineHorizonal(Point other) {
        if (other.getY() == this.y) {
            return true;
        }
        return false;
    }

    /**
     * checks if the x is on the same x as the other point.
     * @param other - a point to check
     * @return if the x is the same, return true. otherwise return false.
     */
    public boolean sameLineVertical(Point other) {
        if (other.getX() == this.x) {
            return true;
        }
        return false;
    }

    /**
     * checks which of the points is at a closer distance.
     * @param first - first point
     * @param second - second point
     * @return the closer point between the two.
     */
    public Point closerDistance(Point first, Point second) {
        if (this.distance(first) < this.distance(second)) {
            return new Point(first.getX(), first.getY());
        } else {
            return new Point(second.getX(), second.getY());
        }
    }

    /**
     * Uses pitagoras to calcualte the distance between the two points.
     * @param otherP - second point
     * @return double distance between the points
     * */
    public double distance(Point otherP) {
        double temp = Math.pow(this.x - otherP.getX(), 2)
                + Math.pow(this.y - otherP.getY(), 2);
        return Math.sqrt(temp);
    }

    /**
     * .
     * @return x coordinate of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * .
     * @return y coordinate of the point.
     */
    public double getY() {
        return this.y;
    }


    /**
     * Checks if the point's X is between 2 other points.
     * @param first - second area point
     * @param second - first area point
     * @return true if it is, false if it isn't
     * */
    public boolean isBetween(Point first, Point second) {
        Point bigger, smaller;
        if (first.getX() > second.getX()) {
            bigger = first;
            smaller = second;
        } else {
            bigger = second;
            smaller = first;
        }

        return isBetweenAtAll(smaller, bigger);

    }


    /**
     * Is the point in between.
     * @param smaller - smaller point
     * @param bigger - bigger point
     * @return true or false, if it is in the middle
     */
    public boolean isBetweenAtAll(Point smaller, Point bigger) {

        if ((this.x >= smaller.getX()) && (this.x <= bigger.getX())) {
            return true;
        }
        return false;

    }


    /**
     * Checks if the point's Y is between 2 other points.
     * @param first - second area point
     * @param second - first area point
     * @return true if it is, false if it isn't
     * */
    public boolean isBetweenY(Point first, Point second) {
        Point bigger, smaller;

        // Checking which point x coordinate is bigger
        if (first.getY() > second.getY()) {
            bigger = first;
            smaller = second;
        } else {
            bigger = second;
            smaller = first;
        }

        return isBetweenAtAllY(smaller, bigger);
    }

    /**
     * Is the point in between.
     * @param smaller - smaller point
     * @param bigger - bigger point
     * @return true or false, if it is in the middle
     */
    public boolean isBetweenAtAllY(Point smaller, Point bigger) {

        if ((this.y >= smaller.getY()) && (this.y <= bigger.getY())) {
            return true;
        }
        return false;

    }




    /**
     * Checks if two points are equals by comparing their X and Y.
     * @param other - other point to compare to
     * @return if the two points equal, return true. otherwise, false.
     * */
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }
}