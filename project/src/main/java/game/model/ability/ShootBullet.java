package game.model.ability;

import game.model.IMovable;
import game.model.ability.action.IAbilityAction;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Projectile;
import game.model.level.ILevel;
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.List;

public class ShootBullet extends Ability {

    public ShootBullet(IMovable user, IMovable target, long cooldown, double bulletRadius, double maxForce, double maxSpeed, int damage) {
        super(cooldown, new IAbilityAction() {
            @Override
            public double getRadius() {
                return bulletRadius;
            }

            @Override
            public double getDuration() {
                return -1; // No duration
            }

            @Override
            // Adds a bullet with the given radius, maxForce, maxSpeed, damage to the given ILevels list of
            // projectiles. The bullet is fired from the position of the user and fired towards the position of the
            // user.
            public void apply(ILevel level, double timePassed) {
                Point2D bulletVelocity = user.getPosition().subtract(target.getPosition());
                bulletVelocity = Utils.limit(bulletVelocity, maxSpeed);
                Projectile bullet = new Bullet(user.getPosition(), bulletRadius, maxForce, maxSpeed, damage, bulletVelocity);
                List<IProjectile<?>> projectiles = level.getProjectiles();
                projectiles.add(bullet);
            }
        });
    }

}
