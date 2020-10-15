package game.util;

import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;
import javafx.scene.transform.Rotate;
import java.util.List;

// Helper class for working with shapes.
public class Shapes {

    private Shapes(){}

    // Detects collisions between shapes using the separating axis theorem. If a collision has occurred, the
    // minimum translation vector will be returned (pointing from shape2 to shape1). If no collision has occurred,
    // returns null.
    public static Point2D testCollision(IShape2D shape1, Point2D position1, IShape2D shape2, Point2D position2) {
        // Check if collision is possible. If not, return null.
        if (shape1.largestInnerDistance() + shape2.largestInnerDistance() < position1.distance(position2)) {
            return null;
        }

        // Find axes to project shapes onto.
        List<Point2D> axes = shape1.getAxes(position1, shape2, position2);
        axes.addAll(shape2.getAxes(position2, shape1, position1));

        // The direction of the minimum translation vector.
        Point2D mtv = new Point2D(0, 0);

        // The magnitude of the minimum translation vector.
        double minOverlap = Double.MAX_VALUE;


        // Loop through axes and look for overlap.
        for (Point2D axis : axes) {
            double[] projection1 = shape1.projection(axis, position1);
            double[] projection2 = shape2.projection(axis, position2);

            // Get the overlap of the shapes on the current axis.
            double overlap = overlap(projection1, projection2);

            // If, for some axis, the shapes do not overlap, return null since a collision cannot have occurred.
            if (overlap == 0) {
                return null;
            }

            if (overlap < minOverlap) {
                minOverlap = overlap;
                mtv = axis;
            }
        }
        // If overlap is found for every axis, a collision has occurred.

        // Vector pointing from center of shape2 to center of shape1
        Point2D difVector = position1.subtract(position2);

        // If mtv is pointing in the wrong direction.
        if (mtv.dotProduct(difVector) < 0) {
            mtv = mtv.multiply(-1);
        }

        // Return a vector with the minimum distance and direction that a shape has to move to undo the collision.
        return Utils.setMagnitude(mtv, minOverlap);
    }

    // Rotates every point in the given list of points around the pivot by the specified amount in radians.
    public static void rotatePoints(List<Point2D> points, Point2D pivot, double rad) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(pivot.getX());
        rotate.setPivotY(pivot.getY());
        rotate.setAngle(Math.toDegrees(rad));

        for (int i = 0; i < points.size(); i++) {
            points.set(i, rotate.transform(points.get(i)));
        }
    }

    /*
    // Takes two double[] where each array holds the min and max magnitude of a shapes projected points on
    // a line. Element 0 is the min value. Element 1 is the max value. Returns true if the projections overlap.
    private static boolean overlap(double[] r1Projection, double[] r2Projection) { //TODO: REMOVE
        if (r1Projection.length != 2 || r2Projection.length != 2) {
            throw new IllegalArgumentException();
        }

        // Check if max projection magnitude of rectangle 1 is smaller than min projection value of rectangle 2,
        // and vice versa. I.e. if there is a gap between the rectangles. Returns true if there is an overlap,
        // else false.
        return (!(r2Projection[1] < r1Projection[0] || r1Projection[1] < r2Projection[0]));
    }
     */

    private static double overlap(double[] s1Projection, double[] s2Projection) {
        if (s1Projection.length != 2 || s2Projection.length != 2) {
            throw new IllegalArgumentException();
        }

        // True if there is no overlap.
        if (s2Projection[1] < s1Projection[0] || s1Projection[1] < s2Projection[0]) return 0;

        // If shape2s max projection is greater than shape1s min.
        if (s2Projection[1] > s1Projection[0]) return s2Projection[1] - s1Projection[0];

        // If shape1s max projection is greater than shape2s min.
        else return s1Projection[1] - s2Projection[0];

    }

    // Projects a given list of points onto the given vector. Returns the min- and max projections as an array of doubles.
    public static double[] project(Point2D axis, List<Point2D> points) {

        // Project first point onto the axis to get a starting value for max and min.
        double min = axis.dotProduct(points.get(0));
        double max = min;

        // Loop over points to find smallest and largest projections.
        for (int i = 1; i < points.size(); i++) {

            // Project current onto the given axis.
            double projection = axis.dotProduct(points.get(i));

            if (projection < min) {
                min = projection;
            }

            else if (projection > max) {
                max = projection;
            }
        }

        // Return double array where first element is min and second is max.
        return new double[]{min, max};
    }

}
