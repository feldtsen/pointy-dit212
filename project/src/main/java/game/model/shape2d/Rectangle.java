package game.model.shape2d;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Rectangle implements IRectangle{
    private double width;
    private double height;
    private double rotation;

    public Rectangle(double width, double height, double rotation) {
        this.height = height;
        this.width = width;
        this.rotation = rotation;
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
    public List<Point2D> getAxes(Point2D position1, IShape2D shape2, Point2D position2) {
        List<Point2D> points = getPoints(position1);
        Shapes.rotatePoints(points, position1, rotation);

        // Get vectors from first to second corner and second to third corner, and normalize.
        List<Point2D> axes = new ArrayList<>();
        axes.add(points.get(0).subtract(points.get(1).normalize()));
        axes.add(points.get(1).subtract(points.get(2)).normalize());

        return axes;
    }

    @Override
    public double[] project(Point2D axis, Point2D position) {
        return new double[0];
    }

    @Override
    public List<Point2D> getPoints(Point2D position) {
        return null;
    }
}
