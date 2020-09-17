package game.model.shape2d;

import javafx.geometry.Point2D;

import java.util.Arrays;

public class Shapes {

    private Shapes(){}

    public static boolean testCollision(ICircle c1, Point2D c1Position, ICircle c2, Point2D c2Position){
        double radiusSum = c1.getRadius() + c2.getRadius();
        Point2D vector = c2Position.subtract(c1Position);
        return Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) <= Math.pow(radiusSum, 2);
    }

    public static boolean testCollision(IRectangle r1, Point2D r1Position, IRectangle r2 , Point2D r2Position) {
        Point2D[] r1Corners = getCornerCoordinates(r1, r1Position);
        Point2D[] r2Corners = getCornerCoordinates(r2, r2Position);


        return false;
    }

    /*
    Returns the coordinates of the corners of the given rectangle.
    The elements in the array represent the following corners:
    0 - top left
    1 - bottom left
    2 - top right
    3 - bottom left
     */
    private static Point2D[] getCornerCoordinates(IRectangle rectangle, Point2D position) {
        Point2D[] corners = new Point2D[4];

        corners[0] = position.add(new Point2D(-rectangle.getWidth()/2, -rectangle.getHeight()/2)); //Top left
        corners[1] = position.add(new Point2D(-rectangle.getWidth()/2, rectangle.getHeight()/2)); //Bottom left
        corners[2] = position.add(new Point2D(rectangle.getWidth()/2, rectangle.getHeight()/2)); //Top right
        corners[3] = position.add(new Point2D(rectangle.getWidth()/2, -rectangle.getHeight()/2)); //Bottom right

        return corners;
    }

}
