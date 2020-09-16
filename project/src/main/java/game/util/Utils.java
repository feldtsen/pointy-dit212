package game.util;

import javafx.geometry.Point2D;

public class Utils {
    /* Private constructor to ensure class cannot be instantiated */
    private Utils() {
    }

    public static Point2D limit(Point2D vector, double maxMagnitude) {
        if(vector == null || maxMagnitude < 0) throw new IllegalArgumentException();

        /* Checks for the square of the magnitude instead of the magnitude itself, to avoid having to use
         * the sqrt operator.
         */
        if(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) > maxMagnitude * maxMagnitude) {
            double magnitude = vector.magnitude();
            double factor = maxMagnitude / magnitude;
            return vector.multiply(factor);
        } else {
            return vector;
        }
    }
}
