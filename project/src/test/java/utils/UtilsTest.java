package utils;

import game.util.Utils;
import javafx.geometry.Point2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {
    /* Tests for setMagnitude method */
    @Test(expected = IllegalArgumentException.class)
    public void testSetMagnitudeNullVector() {
        Utils.setMagnitude(null, 10);
    }

    @Test
    public void testSetMagnitude() {
        Point2D vector = new Point2D(4, 7);
        double newMagnitude = 3;
        Point2D newVector = Utils.setMagnitude(vector, newMagnitude);

        /* Check if magnitude of limited vector is the same as limit value */
        assertEquals(newMagnitude, newVector.magnitude(), 0.0000001);

        /* Create simple vector to compare angle against */
        Point2D compare = new Point2D(1, 0);

        /* Check if angle of vector is the same as that of limited with respect to compare vector */
        assertEquals(vector.angle(compare), newVector.angle(compare), 0.0);

        /* Ensure the angle is the same */
        assertEquals(vector.angle(newVector), 0.0, 0.0);
    }


    /* Tests for limit method */
    @Test
    public void testLimitNoLimit() {
        double limit = 10;
        Point2D vector = new Point2D(5, 5);
        Point2D limited = Utils.limit(vector, limit);
        assertEquals(vector, limited);
    }

    @Test
    public void testLimitLimited() {
        double limit = 5;
        Point2D vector = new Point2D(10, 10);
        Point2D limited = Utils.limit(vector, limit);

        /* Check if magnitude of limited vector is the same as limit value */
        assertEquals(limit, limited.magnitude(), 0.0);

        /* Create simple vector to compare angle against */
        Point2D compare = new Point2D(1, 0);

        /* Check if angle of vector is the same as that of limited with respect to compare vector */
        assertEquals(vector.angle(compare), limited.angle(compare), 0.0);

        /* Ensure the angle is the same */
        assertEquals(vector.angle(limited), 0.0, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLimitNullVector() {
        Utils.limit(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLimitNegativeLimit() {
        Utils.limit(new Point2D(1, 1), -2);
    }

    @Test
    public void testHeading() {
        for(int i = 0; i < 100; i++) {
            double angle = Math.random() * Math.PI * 2;
            double x = Math.cos(angle);
            double y = Math.sin(angle);

            double heading = Utils.heading(new Point2D(x, y));
            assertEquals(angle, heading, 0.0000001);
        }
    }

    @Test
    public void testFromHeading() {
        for(int i = 0; i < 100; i++) {
            double angle = Math.random() * Math.PI * 2;
            double length = Math.abs(Math.random() * 10);

            Point2D vector = Utils.vectorFromHeading(angle, length);
            assertEquals(angle, Utils.heading(vector), 0.00000001);
            assertEquals(length, vector.magnitude(), 0.00000001);
        }
    }
}
