package game.model.shape2d;

import javafx.geometry.Point2D;

import java.util.Arrays;

public class Shapes {

    private Shapes(){}

    public static boolean testCollision(ICircle c1, Point2D c1Position, ICircle c2, Point2D c2Position){
        double radiusSum = c1.getRadius() + c2.getRadius();
        Point2D vector = c2Position.subtract(c1Position);
        return Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) <= Math.pow(radiusSum, 2);
    }

    public static boolean testCollision(IRectangle r1, Point2D r1Position, IRectangle r2 , Point2D r2Position) {
        Point2D[] r1Corners = getCornerCoordinates(r1, r1Position); // Corners of first rectangle
        Point2D[] r2Corners = getCornerCoordinates(r2, r2Position); // Corners of second rectangle




        return false;
    }

    // Returns the coordinates of the corners of the given rectangle.
    private static Point2D[] getCornerCoordinates(IRectangle rectangle, Point2D position) {
        Point2D[] corners = new Point2D[4];

        corners[0] = position.add(new Point2D(-rectangle.getWidth()/2, -rectangle.getHeight()/2)); //Top left
        corners[1] = position.add(new Point2D(-rectangle.getWidth()/2, rectangle.getHeight()/2)); //Bottom left
        corners[2] = position.add(new Point2D(rectangle.getWidth()/2, rectangle.getHeight()/2)); //Top right
        corners[3] = position.add(new Point2D(rectangle.getWidth()/2, -rectangle.getHeight()/2)); //Bottom right

        return corners;
    }

    // Takes an array of a rectangles corners and returns an array of axes parallel to the edges of the rectangle.
    private static Point2D[] getRectangleAxes(Point2D[] corners) {
        Point2D[] axes = new Point2D[corners.length];

        for (int i = 0; i < axes.length - 1; i++) {
            Point2D vector = corners[i].subtract(corners[i + 1]).normalize();
            // Get normal???
            axes[i] = vector;
        }
        Point2D vector = corners[axes.length - 1].subtract(corners[0]).normalize();
        axes[axes.length - 1] = vector;

        return axes;
    }

}
