package game.model.ability;

import game.model.ability.action.AbilityAction;
import game.model.ability.action.IAbilityAction;
import game.model.entity.Entity;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Projectile;
import game.model.level.ILevel;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.List;

public class ShootBullet extends Shoot {
    private final double bulletRadius;
    private final double maxForce;
    private final double maxSpeed;
    private final int strength;

    public ShootBullet(long cooldown, double bulletRadius, double maxForce, double maxSpeed, int strength) {
        super(cooldown);
        this.bulletRadius = bulletRadius;
        this.strength = strength;
        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;
    }

    @Override
    IProjectile<?> createProjectile(IEntity<?> user, IEntity<?> target) {
        // Gets velocity by subtracting the users position from the targets position and setting speed to max.
        Point2D bulletVelocity = target.getPosition().subtract(user.getPosition());
        bulletVelocity = Utils.setMagnitude(bulletVelocity, maxSpeed);

        // Create new bullet.
        Projectile<ICircle> bullet = new Bullet(user.getPosition(), bulletRadius, maxForce, maxSpeed, strength, bulletVelocity);
        return bullet;
    }
}
