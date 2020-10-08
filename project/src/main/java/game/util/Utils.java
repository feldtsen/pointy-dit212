package game.util;

import javafx.geometry.Point2D;

public class Utils {
    /* Private constructor to ensure class cannot be instantiated */
    private Utils() {
    }

    /* Sets magnitude of vector to magnitude */
    public static Point2D setMagnitude(Point2D vector, double magnitude) {
        if(vector == null) throw new IllegalArgumentException();
        return vector.normalize().multiply(magnitude);
    }

    /* Sets the magnitude of vector to maxMagnitude if vector.magnitude() exceeds maxMagnitude */
    public static Point2D limit(Point2D vector, double maxMagnitude) {
        if(vector == null || maxMagnitude < 0) throw new IllegalArgumentException();

        /* Checks for the square of the magnitude instead of the magnitude itself, to avoid having to use
         * the sqrt operator.
         */
        if(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) > maxMagnitude * maxMagnitude) {
            return setMagnitude(vector, maxMagnitude);
        } else {
            return vector;
        }
    }

    // Sets the magnitude of vector to minMagnitude if vector.magnitude subceeds minMagnitude.
    public static Point2D lowerLimit(Point2D vector, double minMagnitude) {
        if (vector == null || minMagnitude < 0) throw new IllegalArgumentException();

        if (Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) < minMagnitude * minMagnitude) {
            return setMagnitude(vector, minMagnitude);
        }
        else {
            return vector;
        }
    }

    // Calculates the heading angle of a vector
    public static double heading(Point2D vector) {
        double a = Math.atan2(vector.getY(), vector.getX());
        return (2 * Math.PI + a) % (2 * Math.PI);
    }

    // Creates a vector of a particular angle and length
    public static Point2D vectorFromHeading(double heading, double length) {
        return new Point2D(Math.cos(heading), Math.sin(heading)).multiply(length);
    }

    // Converts radians to degrees
    public static double radianToDegrees(double radians) {
        return radians * (180 / Math.PI);
    }

    public static double limitAngle(double angle) {
        while(angle < 0)            angle += Math.PI * 2;
        while(angle >= Math.PI * 2) angle -= Math.PI * 2;
        return angle;
    }

}
