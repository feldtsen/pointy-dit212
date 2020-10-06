package game.model.shape2d;

import game.util.Shapes;
import game.util.Utils;
import game.view.IVisitor;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Triangle implements ITriangle{
    private final double width;
    private final double height;

    private double rotation;

    public Triangle(double width, double height, double rotation) {
        this.rotation = rotation;
        this.width = width;
        this.height = height;
    }


    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(double rotation)  {
        this.rotation = rotation;
    }

    @Override
    // Returns a list of normalized vectors orthogonal to the sides of the triangle
    public List<Point2D> getAxes(Point2D position1, IShape2D shape2, Point2D position2) {
        List<Point2D> points = getPoints(position1);
        List<Point2D> axes = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            // Get vector between two corners.
            Point2D vector = points.get(i).subtract(points.get((i + 1) % points.size()));

            // Get normal and set length to 1.
            Point2D normal = new Point2D(-vector.getY(), vector.getX()).normalize();

            axes.add(normal);
        }

        return axes;
    }

    @Override
    // Takes the position of the triangle and a vector, and returns an array containing the minimum and maximum
    // projection of the triangles corners onto the given vector.
    //TODO: duplicate code with Rectangle. Probably better to move this to Shapes.
    public double[] projection(Point2D axis, Point2D position) {

        List<Point2D> corners = getPoints(position);

        double min = axis.dotProduct(corners.get(0));
        double max = min;
        for (int i = 1; i < corners.size(); i++) {
            double projection = axis.dotProduct(corners.get(i));

            if (projection < min) {
                min = projection;
            }
            else if (projection > max) {
                max = projection;
            }
        }

        return new double[]{min, max};
    }

    @Override
    // Takes the position of the rectangle and returns a list of the corner points.
    public List<Point2D> getPoints(Point2D position) {
        // X- and y-coordinates of the Triangles center point for simplicity.
        double x = position.getX();
        double y = position.getY();

        List<Point2D> cornerPoints = new ArrayList<>();
        cornerPoints.add(new Point2D(x + width/2, y - height/2)); // Bottom right
        cornerPoints.add(new Point2D(x - width/2, y - height/2)); // Bottom left
        cornerPoints.add((new Point2D(x, y + height/2))); // Top

        // Rotate the corner points around the center according to the current rotation of the shape.
        Shapes.rotatePoints(cornerPoints, position, rotation);

        return cornerPoints;
    }

    @Override
    // Returns the distance from the center point to the corner that is furthest away.
    public double largestInnerDistance() {
        double dist1 = Math.sqrt(Math.pow(width/2, 2) + Math.pow(height/2, 2)); //Distance from center to one of bottom left and right.
        double dist2 = height/2; // Distance from center to top corner.
        return Math.max(dist1, dist2); // Return max of distances.
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
