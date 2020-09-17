package game.model.shape2d;

import javafx.geometry.Point2D;

public class Shapes {

    private Shapes(){}

    public static boolean testCollision(ICircle c1, Point2D c1Position, ICircle c2, Point2D c2Position){
        double radiusSum = c1.getRadius() + c2.getRadius();
        Point2D vector = c2Position.subtract(c1Position);
        return Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) <= Math.pow(radiusSum, 2);
    }

}
