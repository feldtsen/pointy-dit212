
package model.projectile;

import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.movable.MovableEntity;
import game.model.entity.projectile.Missile;
import game.model.shape2d.Circle;
import game.services.EntityFactory;
import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class MissileTest {
    private MovableEntity<?> target;
    private Missile missile;

    private void init() {
        target = new MovableEntity<>(new Point2D(10, 0), 3, 3,
                new Circle(1)) {
        };

        Point2D velocity = target.getPosition();

        missile = new Missile(new Point2D(0, 0), 0.1, 1, 1, 3, 5,
                1, velocity, target, new SeekingBehaviour());
    }

    @Test
    // Tests that the missile aimed towards a target, changes its velocity as the target moves.
    public void testHoming() {
    init();

    target.update(1, 1);
    missile.update(1, 1);

    // The y-component of the missiles velocity should initially be 0.
        assertEquals(0, missile.getVelocity().getY(), 0.0);

    target.setVelocity(new Point2D(0, 3));
    target.update(1, 1);
    missile.update(1, 1);

    // After moving target straight upwards, the velocity of the missile should be adjusted.
    assertTrue(missile.getVelocity().getY() > 0);
    }

    @Test
    public void testChangingTarget() {
        init();
        MovableEntity<?> newTarget = EntityFactory.basicPlayer(10, 10);

        missile.setTarget(newTarget);

        assertSame(missile.getTarget(), newTarget);
    }
}
