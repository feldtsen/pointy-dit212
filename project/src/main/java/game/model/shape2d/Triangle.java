package game.model.shape2d;

import game.util.Shapes;
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
    // Returns a list of normalized vectors parallel to the edges of the triangle
    public List<Point2D> getAxes(Point2D position1, IShape2D shape2, Point2D position2) {
        return null;
    }

    @Override
    // Takes the position of the rectangle and a vector, and returns an array containing the minimum and maximum
    // projection of the rectangles corners onto the given vector.
    public double[] projection(Point2D axis, Point2D position) {
        return null;
    }

    @Override
    // Takes the position of the rectangle and returns a list of the corner points.
    public List<Point2D> getPoints(Point2D position) {
        return null;
    }

    @Override
    // Returns the distance from the center point to one of the corners.
    public double largestInnerDistance() {
        return 0;
    }
}
