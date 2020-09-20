package game.model.shape2d;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;

import java.util.ArrayList;
import java.util.List;

public class Circle implements ICircle{

    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
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
    public double[] project(Point2D axis, Point2D position) {
        return new double[0];
    }

    @Override
    public List<Point2D> getPoints(Point2D position) {
        return null;
    }
}
