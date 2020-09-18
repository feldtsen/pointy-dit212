package game.model.shape2d;

import javafx.geometry.Point2D;
import javafx.scene.transform.Rotate;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Shapes {

    private Shapes(){}

    public static boolean testCollision(ICircle c1, Point2D c1Position, ICircle c2, Point2D c2Position){
        double radiusSum = c1.getRadius() + c2.getRadius();
        Point2D vector = c2Position.subtract(c1Position);
        return Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) <= Math.pow(radiusSum, 2);
    }

    // Detects collisions between rectangles using the separating axis theorem.
    public static boolean testCollision(IRectangle r1, Point2D r1Position, IRectangle r2 , Point2D r2Position) {
        Point2D[] r1Corners = getCornerCoordinates(r1, r1Position); // Corners of first rectangle
        Point2D[] r2Corners = getCornerCoordinates(r2, r2Position); // Corners of second rectangle

        Point2D[] r1Axes = getRectangleAxes(r1Corners);
        Point2D[] r2Axes = getRectangleAxes(r2Corners);

        // Concatenate r1Axes and r2Axes into axes.
        Point2D[] axes = new Point2D[r1Axes.length + r2Axes.length];
        System.arraycopy(r1Axes, 0, axes, 0, r1Axes.length);
        System.arraycopy(r2Axes, 0, axes, r1Axes.length, r2Axes.length);

        // For every axis, check if there is a gap between the rectangle. If there is, return false since there can
        // be no collision.
        for (Point2D axis : axes) {
            double[] r1Projection = projection(r1Corners, axis);
            double[] r2Projection = projection(r2Corners, axis);

            if (!overlap(r1Projection, r2Projection)) {
                return false;
            }
        }

        // If there is gap found for any axis, then a collision has occurred.
        return true;
    }

    // Takes two double[] where each array holds the min and max magnitude of a rectangles projected points on
    // a line. Element 0 is the min value. Element 1 is the max value.
    private static boolean overlap(double[] r1Projection, double[] r2Projection) {
        if (r1Projection.length != 2 || r2Projection.length != 2) {
            throw new IllegalArgumentException();
        }

        // Check if max projection magnitude of rectangle 1 is smaller than min projection value of rectangle 2,
        // and vice versa. I.e. if there is a gap between the rectangles. Returns true if there is an overlap,
        // else false.
        return (!(r2Projection[1] < r1Projection[0] || r1Projection[1] < r2Projection[0]));
    }

    // Returns the coordinates of the corners of the given rectangle.
    private static Point2D[] getCornerCoordinates(IRectangle rectangle, Point2D position) {
        Point2D[] corners = new Point2D[4];

        corners[0] = position.add(new Point2D(-rectangle.getWidth()/2, -rectangle.getHeight()/2)); //Top left
        corners[1] = position.add(new Point2D(-rectangle.getWidth()/2, rectangle.getHeight()/2)); //Bottom left
        corners[2] = position.add(new Point2D(rectangle.getWidth()/2, rectangle.getHeight()/2)); //Top right
        corners[3] = position.add(new Point2D(rectangle.getWidth()/2, -rectangle.getHeight()/2)); //Bottom right

        // Rotate points according to the rotation of the rectangle.
        rotatePoints(corners, position, rectangle.getRotation());

        return corners;
    }

    // Takes an array of Point2D and rotates every point according to the given pivot point and angle in radians.
    // Modifies the given array.
    private static void rotatePoints(Point2D[] points, Point2D pivot, double rad) {
        // Create Rotate object to rotate points.
        Rotate rotate = new Rotate(); //TODO: Rotate is a part of javaFX. Is this allowed in model?
        rotate.setPivotX(pivot.getX());
        rotate.setPivotY(pivot.getY());
        rotate.setAngle(Math.toDegrees(rad));

        for (int i = 0; i < points.length; i++) {
            points[i] = rotate.transform(points[i]);
        }
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

    // Returns the the largest and smallest projection magnitudes of the rectangle corners projected on the given axis.
    // Element 0 of the returned double[] is the min value. Element 1 is the max value.
    private static double[] projection(Point2D[] rectangleCorners, Point2D axis) {
        double min = axis.dotProduct(rectangleCorners[0]);
        double max = min;

        for (int i = 1; i < rectangleCorners.length; i++) {
            double projectionMagnitude = axis.dotProduct(rectangleCorners[i]);
            if (projectionMagnitude < min) {
                min = projectionMagnitude;
            }
            else if (projectionMagnitude > max) {
                max = projectionMagnitude;
            }
        }
        double[] result = new double[]{min, max};
        return result;
    }

}
