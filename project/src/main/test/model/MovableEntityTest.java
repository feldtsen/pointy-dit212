package model;

import game.model.entity.IEntity;
import game.model.entity.movable.MovableEntity;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MovableEntityTest {
    public MovableEntity movableEntity;

    @Before
    public void before() {
        movableEntity = new MovableEntity(new Point2D(0, 0), 1, 1, 1) {
            @Override
            public boolean handleCollision(IEntity entity) {
                return false;
            }
        };
    }

    @Test
    public void testLimitNoLimit() {
        double limit = 10;
        Point2D vector = new Point2D(5, 5);
        Point2D limited = MovableEntity.limit(vector, limit);
        assertEquals(vector, limited);
    }

    @Test
    public void testLimitLimited() {
        double limit = 5;
        Point2D vector = new Point2D(10, 10);
        Point2D limited = MovableEntity.limit(vector, limit);

        /* Check if magnitude of limited vector is the same as limit value */
        assertEquals(limit, limited.magnitude(), 0.0);

        /* Create simple vector to compare angle against */
        Point2D compare = new Point2D(1, 0);
        /* Check if angle of vector is the same as that of limited with respect to compare vector */
        assertEquals(vector.angle(compare), limited.angle(compare), 0.0);
    }

}
