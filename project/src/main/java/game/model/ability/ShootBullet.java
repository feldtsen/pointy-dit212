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
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.List;

public class ShootBullet extends Ability {
    private double bulletRadius;
    private double maxForce;
    private double maxSpeed;
    private int strength;

    public ShootBullet(long cooldown, double bulletRadius, double maxForce, double maxSpeed, int strength) {
        super(cooldown);
        this.bulletRadius = bulletRadius;
        this.strength = strength;
        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;
    }

    @Override
    // The returned AbilityAction creates and adds a bullet to the levels list of projectiles.
    public IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
        IAbilityAction abilityAction = new AbilityAction(user, target, 0) {

            @Override
            public void apply(ILevel level, double timePassed) {

                // Gets velocity by subtracting the users position from the targets position and setting speed to max.
                Point2D bulletVelocity = target.getPosition().subtract(user.getPosition());
                bulletVelocity = Utils.setMagnitude(bulletVelocity, maxSpeed);

                // Create new bullet.
                Projectile bullet = new Bullet(user.getPosition(), bulletRadius, maxForce, maxSpeed, strength, bulletVelocity);

                // Get levels list of projectiles and add bullet.
                List<IProjectile<?>> projectiles = level.getProjectiles();
                projectiles.add(bullet);
            }
        };

        return abilityAction;
    }
}
