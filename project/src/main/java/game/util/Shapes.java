package game.util;

import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;
import javafx.scene.transform.Rotate;
import java.util.List;

// Helper class for working with shapes.
public class Shapes {

    private Shapes(){}

    // Detects collisions between shapes using the separating axis theorem.
    public static boolean testCollision(IShape2D shape1, Point2D position1, IShape2D shape2, Point2D position2) {
        // Check if collision is possible. If not, return false.
        if (shape1.largestInnerDistance() + shape2.largestInnerDistance() < position1.distance(position2)) {
            return false;
        }

        // Find axes to project shapes onto.
        List<Point2D> axes = shape1.getAxes(position1, shape2, position2);
        axes.addAll(shape2.getAxes(position2, shape1, position1));

        // Loop through axes and look for overlap.
        for (Point2D axis : axes) {
            double[] projection1 = shape1.projection(axis, position1);
            double[] projection2 = shape2.projection(axis, position2);

            // If, for some axis, the shapes do not overlap, return false since there cannot be a collision.
            if (!overlap(projection1, projection2)) {
                return false;
            }
        }
        // If overlap is found for every axis, a collision has occurred.
        return true;
    }

    // Rotates every point in the given list of points around the pivot by the specified amount in radians.
    public static void rotatePoints(List<Point2D> points, Point2D pivot, double rad) {
        Rotate rotate = new Rotate(); //TODO: Rotate is a part of javaFX. Is this allowed in model?
        rotate.setPivotX(pivot.getX());
        rotate.setPivotY(pivot.getY());
        rotate.setAngle(Math.toDegrees(rad));

        for (int i = 0; i < points.size(); i++) {
            points.set(i, rotate.transform(points.get(i)));
        }
    }

    // Takes two double[] where each array holds the min and max magnitude of a shapes projected points on
    // a line. Element 0 is the min value. Element 1 is the max value. Returns true if the projections overlap.
    private static boolean overlap(double[] r1Projection, double[] r2Projection) {
        if (r1Projection.length != 2 || r2Projection.length != 2) {
            throw new IllegalArgumentException();
        }

        // Check if max projection magnitude of rectangle 1 is smaller than min projection value of rectangle 2,
        // and vice versa. I.e. if there is a gap between the rectangles. Returns true if there is an overlap,
        // else false.
        return (!(r2Projection[1] < r1Projection[0] || r1Projection[1] < r2Projection[0]));
    }

}
