package game.model.shape2d;

import javafx.geometry.Point2D;
import javafx.scene.transform.Rotate;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

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

        // If the sum of the distances from the centers to a corner in the rectangle, is greater than the distance
        // between the centers of the rectangles, then a collision is impossible and there is no need to check
        if (r1Position.distance(r1Corners[0]) + r2Position.distance(r2Corners[0]) < r1Position.distance(r2Position)) {
            return false;
        }

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

        // If there is no gap found for any axis, then a collision has occurred.
        return true;
    }

    public static boolean testCollision(IShape2D shape1, Point2D position1, IShape2D shape2, Point2D position2) {
        // Get axes from both shapes - getAxes in IShape2D
        // loop through axes
            // project both shapes on axis and check for overlap
            // return false if overlap
        // Return true

        // Find axes to project shapes onto.
        List<Point2D> axes = shape1.getAxes(position1, shape2, position2);
        axes.addAll(shape2.getAxes(position2, shape1, position1));

        // Loop through axes and look for overlap.
        for (Point2D axis : axes) {
            double[] projection1 = shape1.project(axis);
            double[] projection2 = shape2.project(axis);

            // If, for some axis, the shapes do not overlap, return false since there cannot be a collision.
            if (!overlap(projection1, projection2)) {
                return false;
            }
        }
        // If overlap is found for every axis, a collision has occurred.
        return true;
    }

    // Detects collisions between a rectangle and a circle using separating axis theorem.
    public static boolean testCollision(IRectangle rectangle, Point2D rPosition, ICircle circle, Point2D cPosition) {
        Point2D[] rectCorners = getCornerCoordinates(rectangle, rPosition);

        // Checks if the distance between the shapes is too large for there to have been a collision.
        if (rPosition.distance(rectCorners[0]) + circle.getRadius() < rPosition.distance(cPosition)) {
            return false;
        }

        // Find the rectangle corner that is closest to the center of the circle.
        Point2D closestCorner = rectCorners[0];
        for (int i = 0; i < rectCorners.length; i++) {
            if (rectCorners[i].distance(cPosition) < closestCorner.distance(cPosition)) {
                closestCorner = rectCorners[i];
            }
        }

        // Checks if the nearest corner is inside the circle.
        if (closestCorner.distance(cPosition) <= circle.getRadius()) {
            return true;
        }

        Point2D[] axes = new Point2D[rectCorners.length + 1];

        // Add axes from rectangle to array.
        System.arraycopy(getRectangleAxes(rectCorners), 0, axes, 0, rectCorners.length);

        // Get an axis from circle by drawing a vector from the closest corner to the midpoint of the circle.
        Point2D circleAxis = closestCorner.subtract(cPosition).normalize();
        axes[axes.length - 1] = circleAxis;

        // Loop through axes and see if there is overlap in the projections of the circle and the rectangle.
        for (Point2D axis : axes) {
            double[] rectangleProjections = projection(rectCorners, axis);

            // Find the min/max projections of the circle by finding the projection of the midpoint and
            // subtracting/adding the radius.
            double circleProjection = axis.dotProduct(cPosition);
            double[] circleProjections = new double[]{circleProjection - circle.getRadius(),
                    circleProjection + circle.getRadius()};

            // If for some axis there is no overlap, return false since there cannot be a collision.
            if (!(overlap(rectangleProjections, circleProjections))) {
                return false;
            }
        }
        // True if there is overlap for every axis.
        return true;
    }

    // Returns the coordinates of the corners of the given rectangle.
    public static Point2D[] getCornerCoordinates(IRectangle rectangle, Point2D position) {
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
    public static void rotatePoints(Point2D[] points, Point2D pivot, double rad) {
        // Create Rotate object to rotate points.
        Rotate rotate = new Rotate(); //TODO: Rotate is a part of javaFX. Is this allowed in model?
        rotate.setPivotX(pivot.getX());
        rotate.setPivotY(pivot.getY());
        rotate.setAngle(Math.toDegrees(rad));

        for (int i = 0; i < points.length; i++) {
            points[i] = rotate.transform(points[i]);
        }
    }

    public static void rotatePoints(List<Point2D> points, Point2D pivot, double rad) {
        Rotate rotate = new Rotate(); //TODO: Rotate is a part of javaFX. Is this allowed in model?
        rotate.setPivotX(pivot.getX());
        rotate.setPivotY(pivot.getY());
        rotate.setAngle(Math.toDegrees(rad));

        for (int i = 0; i < points.size(); i++) {
            points.set(i, rotate.transform(points.get(i)));
        }
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

    //TODO: getRectangleAxes doesn't need to return 4 axes since every axis there will be another parallel axis.
    //      2 will be enough

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
