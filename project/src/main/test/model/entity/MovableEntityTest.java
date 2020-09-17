package model.entity;

import game.model.entity.IEntity;
import game.model.entity.movable.MovableEntity;
import game.util.Utils;
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
        movableEntity = new MovableEntity(new Point2D(0, 0), 1, maxForce, maxSpeed) {};
    }

    @Test
    public void testMove() {
        Point2D delta = new Point2D(2, 2);
        Point2D current = movableEntity.getPosition();
        movableEntity.move(delta);
        assertEquals(current.add(delta), movableEntity.getPosition());
    }

    @Test
    public void testVelocityUpdate() {
        Point2D velocity = new Point2D(1, 1);
        Point2D currentPosition = movableEntity.getPosition();

        movableEntity.setVelocity(velocity);

        /* Delta should not matter for velocity */
        //TODO: is this good!?!?!?
        movableEntity.update(1);

        assertEquals(currentPosition.add(velocity), movableEntity.getPosition());
    }

    @Test
    public void testOverMaxVelocityUpdate() {
        Point2D velocity = new Point2D(10, 10);
        Point2D currentPosition = movableEntity.getPosition();

        movableEntity.setVelocity(velocity);

        /* Delta should not matter for velocity */
        movableEntity.update(1);

        assertEquals(currentPosition.add(Utils.limit(velocity, maxSpeed)), movableEntity.getPosition());
    }

    @Test
    public void testAccelerationUpdate() {
        Point2D force = new Point2D(2, 2);
        Point2D currentPosition = movableEntity.getPosition();
        Point2D currentVelocity = movableEntity.getVelocity();

        movableEntity.addForce(force);
        movableEntity.update(1.0); //delta 1.0 ==> 1 second has passed since last update

        assertEquals(currentVelocity.add(force), movableEntity.getVelocity());
        assertEquals(currentPosition.add(currentVelocity.add(force)), movableEntity.getPosition());
    }

    @Test
    public void testOverMaxAccelerationUpdate() {
        Point2D force = new Point2D(10, 10);
        Point2D currentPosition = movableEntity.getPosition();
        Point2D currentVelocity = movableEntity.getVelocity();

        movableEntity.addForce(force);
        movableEntity.update(1.0);

        assertEquals(currentVelocity.add(Utils.limit(force, maxForce)), movableEntity.getVelocity());
        assertEquals(currentPosition.add(currentVelocity.add(Utils.limit(force, maxForce))), movableEntity.getPosition());
    }
}
