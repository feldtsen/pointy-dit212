package utils;

import game.util.Utils;
import javafx.geometry.Point2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {
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
}
