package model;

import game.model.entity.IEntity;
import game.model.entity.movable.MovableEntity;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MovableEntityTest {
    public MovableEntity movableEntity;
    private double maxForce;
    private double maxSpeed;

    @Before
    public void before() {
        maxForce = 3;
        maxSpeed = 3;
        movableEntity = new MovableEntity(new Point2D(0, 0), 1, maxForce, maxSpeed) {
            @Override
            public boolean handleCollision(IEntity entity) {
                return false;
            }
        };
    }

    /* Limit helper method testing */
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

        /* Ensure the angle is the same */
        assertEquals(vector.angle(limited), 0.0, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLimitNullVector() {
        MovableEntity.limit(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLimitNegativeLimit() {
        MovableEntity.limit(new Point2D(1, 1), -2);
    }

    /* IMovable testing */
    @Test
    public void testMove() {
        Point2D delta = new Point2D(2, 2);
        Point2D current = movableEntity.getPosition();
        movableEntity.move(delta);
        assertEquals(current.add(delta), movableEntity.getPosition());
    }

    @Test
    public void testVelocityPositionUpdate() {
        Point2D velocity = new Point2D(1, 1);
        Point2D currentPosition = movableEntity.getPosition();

        movableEntity.setVelocity(velocity);

        /* Delta should not matter for velocity */
        //TODO: is this good!?!?!?
        movableEntity.update(1);

        assertEquals(currentPosition.add(velocity), movableEntity.getPosition());
    }

    @Test
    public void testOverMaxVelocityPositionUpdate() {
        Point2D velocity = new Point2D(10, 10);
        Point2D currentPosition = movableEntity.getPosition();

        movableEntity.setVelocity(velocity);

        /* Delta should not matter for velocity */
        movableEntity.update(1);

        assertEquals(currentPosition.add(MovableEntity.limit(velocity, maxSpeed)), movableEntity.getPosition());
    }
}
