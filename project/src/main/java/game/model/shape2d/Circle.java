package game.model.shape2d;

import game.view.IShapeVisitor;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Circle implements ICircle {
    private double radius;
    private final double originRadius;
    private double rotation;

    public Circle(double radius){
        this.radius = radius;
        originRadius = radius;
        this.rotation = 0;
    }


    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    // Returns a list containing one normalized vector parallel to the line from the center of the circle to the
    // closest point of the other shape.
    public List<Point2D> getAxes(Point2D position1, IShape2D shape2, Point2D position2) {
        List<Point2D> shape2Points = shape2.getPoints(position2);

        Point2D closestPoint = shape2Points.get(0);
        for (int i = 1; i < shape2Points.size(); i++) {
            Point2D currentPoint = shape2Points.get(i);
            if (position1.distance(currentPoint) < position1.distance(closestPoint)) {
                closestPoint = currentPoint;
            }
        }
        // Return a normalized vector from the center of the circle to the closest point of the other shape.
        Point2D axis = position1.subtract(closestPoint).normalize();
        List<Point2D> result = new ArrayList<>();
        result.add(axis);
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
    public void resize(double scaleRatio) {
        this.radius = originRadius * scaleRatio;
    }

    @Override
    public void acceptShapeVisitor(IShapeVisitor visitor) {
        visitor.visit(this);
    }
}
