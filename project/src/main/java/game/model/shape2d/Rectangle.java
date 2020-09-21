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
    // Returns a list of normalized vectors parallel to the edges of the rectangle.
    public List<Point2D> getAxes(Point2D position1, IShape2D shape2, Point2D position2) {
        List<Point2D> points = getPoints(position1);

        // Get vectors from first to second corner and second to third corner, and normalize.
        List<Point2D> axes = new ArrayList<>();
        axes.add(points.get(0).subtract(points.get(1)).normalize());
        axes.add(points.get(1).subtract(points.get(2)).normalize());

        return axes;
    }

    @Override
    // Takes the position of the rectangle and a vector, and returns an array containing the minimum and maximum
    // projection of the rectangles corners onto the given vector.
    public double[] projection(Point2D axis, Point2D position) {
        List<Point2D> cornerPoints = getPoints(position);

        double min = axis.dotProduct(cornerPoints.get(0));
        double max = min;

        for (int i = 1; i < cornerPoints.size(); i++) {
            double projection = axis.dotProduct(cornerPoints.get(i));
            if (projection < min) {
                min = projection;
            }
            else if (projection >  max) {
                max = projection;
            }
        }
        return new double[]{min, max};
    }

    @Override
    // Takes the position of the rectangle and returns a list of the corner points.
    public List<Point2D> getPoints(Point2D position) {
        List<Point2D> cornerPoints = new ArrayList<>();

        double x = position.getX();
        double y = position.getY();
        cornerPoints.add(new Point2D(x - width/2, y + height/2));
        cornerPoints.add(new Point2D(x - width/2, y - height/2));
        cornerPoints.add(new Point2D(x + width/2, y - height/2));
        cornerPoints.add(new Point2D(x + width/2, y + height/2));

        Shapes.rotatePoints(cornerPoints, position, rotation);
        return cornerPoints;
    }
}
