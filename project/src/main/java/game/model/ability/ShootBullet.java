package game.model.ability;

import game.model.entity.IEntity;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.util.Utils;
import javafx.geometry.Point2D;


// Ability for shooting bullets
public class ShootBullet extends Shoot {
    private final double bulletRadius; // The radius of the bullet
    private final double maxForce;     // The max force of the bullet
    private final double maxSpeed;     // The max speed of the bullet
    private final int strength;        // The strength of the bullet

    public ShootBullet(long cooldown, double bulletRadius, double maxForce, double maxSpeed, int strength) {
        super(cooldown);
        this.bulletRadius = bulletRadius;
        this.strength = strength;
        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;
    }

    @Override
    protected IProjectile<?> createProjectile(IEntity<?> user, IEntity<?> target) {
        // Gets velocity by subtracting the users position from the targets position and setting speed to max.
        Point2D bulletVelocity = target.getPosition().subtract(user.getPosition());
        bulletVelocity = Utils.setMagnitude(bulletVelocity, maxSpeed);

        // Create and return new bullet at the position of the user
        return new Bullet(user.getPosition(), bulletRadius, maxForce, maxSpeed, strength, bulletVelocity);
    }
}
