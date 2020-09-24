package game.model.ability;

import game.model.ability.action.IAbilityAction;
import game.model.entity.Entity;
import game.model.entity.movable.LivingEntity;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Projectile;
import game.model.level.ILevel;
import game.model.shape2d.Circle;
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.List;

public class ShootBullet extends Ability {
    private Entity<?> user;
    private Entity<?> target;

    public ShootBullet(Entity<?> user, LivingEntity<?> target, long cooldown, double bulletRadius, double maxForce, double maxSpeed, int damage) {
        super(cooldown, new IAbilityAction() {
            @Override
            public double getDuration() {
                return -1; // No duration
            }

            @Override
            // Adds a bullet with the given radius, maxForce, maxSpeed, damage to the given levels list of
            // projectiles. The bullet is fired from the position of the user and fired towards the position of the
            // target.
            public void apply(ILevel level, double timePassed) {
                // Only add a new projectile if the target is alive and not null.
                if (target == null || !target.isAlive()) return;

                // Gets velocity by subtracting targets position from user and setting speed to maxSpeed.
                Point2D bulletVelocity = target.getPosition().subtract(user.getPosition());
                bulletVelocity = Utils.setMagnitude(bulletVelocity, maxSpeed);

                // Create new bullet
                Projectile bullet = new Bullet(user.getPosition(), bulletRadius, maxForce, maxSpeed, damage, bulletVelocity);

                // Get list of projectiles and add new bullet.
                List<IProjectile<?>> projectiles = level.getProjectiles();
                projectiles.add(bullet);
            }
        });
    }
}
