
import java.util.ArrayList;
import java.util.List;

/**
 * @author orel
 */
public class Rectangle {

    private double width;
    private double height;
    private Point upperLeft;

    /**
     * Uses upper left point to create a new rectangle.
     * @param upperLeft - upper left point
     * @param width - the rectangles width
     * @param height - the rectangles height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        Point p = new Point(upperLeft.getX(), upperLeft.getY());
        this.upperLeft = p;
        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }


    /**
     * Returns a list of intersection points between the line and the rectangle.
     * @param lineIntersecting - the line to check the intersection points with
     * @return A list of intersection points. For no intersections, returns an empty list.
     */
    public List<Point> intersectionPoints(Line lineIntersecting) {
        List<Point> listIntersections = new ArrayList<Point>();

        // Calculating the the rectangle's lines.
        Line downLine = new Line(this.getBottomLeft(), this.getBottomRight());
        Line upLine = new Line(this.getUpperLeft(), this.getUpperRight());
        Line leftLine = new Line(this.getUpperLeft(), this.getBottomLeft());
        Line rightLine = new Line(this.getUpperRight(), this.getBottomRight());
        // Adds the intersection point of 'line' with the rectangle's lines,
        // to 'listIntersections'.

        if (lineIntersecting.isIntersecting(leftLine)) {
            listIntersections.add(lineIntersecting.intersectionWith(leftLine));
        }

        if (lineIntersecting.isIntersecting(downLine)) {
            listIntersections.add(lineIntersecting.intersectionWith(downLine));
        }

        if (lineIntersecting.isIntersecting(upLine)) {
            listIntersections.add(lineIntersecting.intersectionWith(upLine));
        }

        if (lineIntersecting.isIntersecting(rightLine)) {
            listIntersections.add(lineIntersecting.intersectionWith(rightLine));
        }
        return listIntersections;
    }



    /**
     * Returns a copy of the rectangle.
     *
     * @return A rectangle copy
     */
    public Rectangle copy() {
        Point p = new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY());
        return new Rectangle(p, this.width, this.height);
    }

    /**
     * .
     * @return Rectangle's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * .
     * @return (Point) upper right
     */
    public Point getUpperRight() {
        double x = this.upperLeft.getX() + this.getWidth();
        return new Point(x, this.upperLeft.getY());
    }

    /**
     * .
     * @return (Point) bottom left
     */
    public Point getBottomLeft() {
        double y = this.upperLeft.getY() + this.getHeight();
        return new Point(this.upperLeft.getX(), y);
    }



    /**
     * .
     * @return (Point) upper left
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * .
     * @return the rectangle's width
     */
    public double getWidth() {
        return this.width;
    }


    /**
     * .
     * @return (Point) upper right
     */
    public Point getBottomRight() {
        double x = this.upperLeft.getX() + this.getWidth();
        return new Point(x, this.upperLeft.getY() + this.getHeight());
    }
}