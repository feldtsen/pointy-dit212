package game.model.ability;

import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Missile;
import javafx.geometry.Point2D;

public class ShootMissile extends Shoot {
    private double radius;
    private double maxForce;
    private double maxSpeed;
    private double minSpeed;
    private int strength;
    private IMovementBehaviour movementBehaviour;

    public ShootMissile(long cooldown, double radius, double maxForce, double maxSpeed, double minSpeed, int strength,
                        IMovementBehaviour movementBehaviour) {
        super(cooldown);
        this.radius = radius;
        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        this.strength = strength;
        this.movementBehaviour = movementBehaviour;
    }

    @Override
    // Creates and returns a missile starting at the position of the user, aimed at the given target
    IProjectile<?> createProjectile(IEntity<?> user, IEntity<?> target) {
        Point2D missileVelocity = target.getPosition().subtract(user.getPosition());

        Missile missile = new Missile(user.getPosition(), radius, maxForce, minSpeed, maxSpeed, strength, missileVelocity,
                target, movementBehaviour);

        return missile;
    }
}
