
package model.projectile;

import game.model.entity.Entity;
import game.model.entity.movable.MovableEntity;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Missile;
import game.model.entity.projectile.Projectile;
import game.model.shape2d.Circle;
import game.model.shape2d.IShape2D;
import game.util.Utils;
import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class MissileTest {
    private MovableEntity<?> target;
    private Projectile<?> missile;

    private void init() {
        target = new MovableEntity<Circle>(new Point2D(10, 0), 3, 3,
                new Circle(1)) {};

        Point2D velocity = target.getPosition();

        missile = new Missile(new Point2D(0, 0), 0.1, 1, 3, 5,
                1, velocity, target);
    }

    @Test
    // Tests that the missile aimed towards a target, changes its velocity as the target moves.
    public void testHoming() {
    init();

    target.update(1, 1);
    missile.update(1, 1);

    // The y-component of the missiles velocity should initially be 0.
    assertTrue(missile.getVelocity().getY() == 0);

    target.setVelocity(new Point2D(0, 3));
    target.update(1, 1);
    missile.update(1, 1);

    // After moving target straight upwards, the velocity of the missile should be adjusted.
    assertTrue(missile.getVelocity().getY() > 0);
    }
}
