package game.model.shape2d;

import javafx.geometry.Point2D;

import java.util.List;

public interface IShape2D {

    /* Returns a list of normalized vectors (axes) to be used when checking for collisions.
       Args:
       position1 - The position of the shape calling the method.
       shape2    - The other shape.
       position2 - The position of the other shape.
    */
    List<Point2D> getAxes(Point2D position1, IShape2D shape2, Point2D position2);

    double[] project(Point2D axis, Point2D position);

    List<Point2D> getPoints(Point2D position);
}
