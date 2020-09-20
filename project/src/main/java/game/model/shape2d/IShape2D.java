package game.model.shape2d;

import javafx.geometry.Point2D;

import java.util.List;

public interface IShape2D {

    /* Returns a list of normalized vectors (axes) to be used when checking for collisions.
       Args: position1 - The position of the shape calling the method.
             shape2    - The other shape.
             position2 - The position of the other shape.
    */
    List<Point2D> getAxes(Point2D position1, IShape2D shape2, Point2D position2);

    /* Returns an array of the minimum and maximum projection magnitudes of the shape calling the method on the given
       axis. The first element of the array is the min. The second element is the max.
       Args: axis     - The normalized vector to project the shape onto.
             position - The position of the shape.
     */
    double[] projection(Point2D axis, Point2D position);

    // Returns a list of points that the shape consists of. For polygons, this will be a list of the corner points.
    // For circles a list consisting of only the center point will be returned.
    List<Point2D> getPoints(Point2D position);
}
