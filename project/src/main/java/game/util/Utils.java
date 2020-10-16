package game.util;

import javafx.geometry.Point2D;

// Util class for doing vector operations, and more (wow)
public class Utils {
    // Private constructor to ensure class cannot be instantiated
    private Utils() {
    }

    // Sets magnitude of vector to magnitude
    public static Point2D setMagnitude(Point2D vector, double magnitude) {
        if(vector == null) throw new IllegalArgumentException();
        return vector.normalize().multiply(magnitude);
    }

    // Sets the magnitude of vector to maxMagnitude if vector.magnitude() exceeds maxMagnitude
    public static Point2D limit(Point2D vector, double maxMagnitude) {
        if(vector == null || maxMagnitude < 0) throw new IllegalArgumentException();

        // Checks for the square of the magnitude instead of the magnitude itself, to avoid having to u
        // the sqrt operator.
        if(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) > maxMagnitude * maxMagnitude) {
            // Set the magnitude of the vector to maxMagnitude
            return setMagnitude(vector, maxMagnitude);
        } else {
            // Otherwise, return the vector unchanged
            return vector;
        }
    }

    // Sets the magnitude of vector to minMagnitude if the vector magnitude is lower than minMagnitude.
    public static Point2D lowerLimit(Point2D vector, double minMagnitude) {
        if (vector == null || minMagnitude < 0) throw new IllegalArgumentException();

        if (Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) < minMagnitude * minMagnitude) {
            // Set magnitude to minMagnitude
            return setMagnitude(vector, minMagnitude);
        } else {
            // Otherwise return the vector unchanged
            return vector;
        }
    }

    // Calculates the heading angle of a vector
    public static double heading(Point2D vector) {
        double a = Math.atan2(vector.getY(), vector.getX());
        // Map the angle to always be between 0 and 2*PI
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

    // Makes sure an angle is limited to be between 0 and 2*PI
    public static double limitAngle(double angle) {
        while(angle < 0)            angle += Math.PI * 2;
        while(angle >= Math.PI * 2) angle -= Math.PI * 2;
        return angle;
    }

    // Maps a value from one range to another range
    // i.e, if 0.5 is defined to be within the range of 0 and 1, we could remap this to bo within the range of
    // 100 and 200, which would result in a return value of 150.
    public static double map(double oldValue, double oldMin, double oldMax, double newMin, double newMax) {
        double normalized = (oldValue - oldMin) / (oldMax - oldMin);
        return normalized * (newMax - newMin) + newMin;
    }
}
