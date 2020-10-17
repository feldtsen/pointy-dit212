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
        Point2D minimumTranslationVector = new Point2D(0, 0);

        // The magnitude of the minimum translation vector.
        double minOverlap = Double.MAX_VALUE;

        // Loop through axes and look for overlap.
        for (Point2D axis : axes) {
            double[] projection1 = shape1.projection(axis, position1);
            double[] projection2 = shape2.projection(axis, position2);

            // Get the overlap of the shapes on the current axis.
            double overlap = getOverlap(projection1, projection2);

            // If, for some axis, the shapes do not overlap, return null since a collision cannot have occurred.
            if (overlap == -1) {
                return null;
            }

            if (overlap < minOverlap) {
                minOverlap = overlap;
                minimumTranslationVector = axis;
            }
        }
        // If this point is reached, a collision has occurred.

        // Vector pointing from center of shape2 to center of shape1
        Point2D difVector = position1.subtract(position2);

        // Check if mtv is pointing in wrong direction.
        if (minimumTranslationVector.dotProduct(difVector) < 0) {
            minimumTranslationVector = minimumTranslationVector.multiply(-1);
        }

        // If the mtv is returned with minOverlap as magnitude, the new distance between the entities will be 0. Since
        // we defined a collision to still occur if the distance between two entities are 0, we have to add a small
        // value to minOverlap before giving it to the mtv.
        return Utils.setMagnitude(minimumTranslationVector, minOverlap + .000000000001);
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

    // Returns the overlap of two shapes projections. If there is no overlap, -1 is returned.
    private static double getOverlap(double[] shape1Projection, double[] shape2Projection) {
        if (shape1Projection.length != 2 || shape2Projection.length != 2) {
            throw new IllegalArgumentException();
        }

        // True if there is no overlap.
        if (shape2Projection[1] < shape1Projection[0] || shape1Projection[1] < shape2Projection[0]) return -1;

        // True if there is overlap and shape2 has the rightmost point.
        if (shape2Projection[1] > shape1Projection[1]) return shape1Projection[1] - shape2Projection[0];

        // If there is overlap and shape1 has the rightmost point.
        else return shape2Projection[1] - shape1Projection[0];
    }
}
