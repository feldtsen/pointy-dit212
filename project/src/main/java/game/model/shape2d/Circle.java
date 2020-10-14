package game.model.shape2d;

import game.view.IShapeVisitor;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;

// Simple circle shape
public class Circle implements ICircle {
    // Radius of the circle
    private final double radius;

    // The rotation of a circle shape is irrelevant, but since all shapes are defined to have a rotation,
    // circles will have one too.
    private double rotation;

    public Circle(double radius){
        this.radius = radius;
        this.rotation = 0;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    // Returns a list containing one normalized vector from the center of the circle to the closest point of the other shape.
    public List<Point2D> getAxes(Point2D position1, IShape2D shape2, Point2D position2) {

        // Points of the other shape.
        List<Point2D> shape2Points = shape2.getPoints(position2);

        // Loop over the other shapes points to find the closest one.
        Point2D closestPoint = shape2Points.get(0);
        for (int i = 1; i < shape2Points.size(); i++) {
            Point2D currentPoint = shape2Points.get(i);

            // Check if the current point is closer than closestPoint.
            if (position1.distance(currentPoint) < position1.distance(closestPoint)) {
                closestPoint = currentPoint;
            }
        }
        // Create a vector from the closest point of the other shape to the center of the circle. Normalized.
        Point2D axis = position1.subtract(closestPoint).normalize();

        // Create a list containing only the normalized vector.
        List<Point2D> result = new ArrayList<>(List.of(axis));
        return result;
    }

    @Override
    // Takes a vector and the position of the circle. Returns an array of double containing the minimum and maximum
    // value of the circle's projections onto the vector.
    public double[] projection(Point2D axis, Point2D position) {
        // Get magnitude of the projection of the center point onto the axis.
        double centerProjection = axis.dotProduct(position);

        // Get the min and max projection magnitudes.
        double min = centerProjection - radius;
        double max = centerProjection + radius;

        return new double[]{min, max};
    }

    @Override
    // Returns a List containing only the center of the circle.
    public List<Point2D> getPoints(Point2D position) {
        List<Point2D> points = new ArrayList<>();
        points.add(position);
        return points;
    }

    @Override
    // Returns the radius
    public double largestInnerDistance() {
        return radius;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public void acceptShapeVisitor(IShapeVisitor visitor) {
        visitor.visit(this);
    }
}
