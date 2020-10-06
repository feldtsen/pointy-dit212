package game.model.ability;

import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Missile;
import javafx.geometry.Point2D;

// Ability for shooting missiles
public class ShootMissile extends Shoot {
    private final double width;
    private final double height;
    private final double maxForce;
    private final double maxSpeed;
    private final double minSpeed;
    private final int strength;
    private final IMovementBehaviour movementBehaviour;

    public ShootMissile(long cooldown, double width, double height, double maxForce, double maxSpeed, double minSpeed, int strength,
                        IMovementBehaviour movementBehaviour) {
        super(cooldown);
        this.width = width;
        this.height = height;
        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        this.strength = strength;
        this.movementBehaviour = movementBehaviour;
    }

    // Creates and returns a missile starting at the position of the user, aimed at the given target
    @Override
    protected IProjectile<?> createProjectile(IEntity<?> user, IEntity<?> target) {
        // Create initial velocity aimed at the target
        Point2D missileVelocity = target.getPosition().subtract(user.getPosition());

        return new Missile(user.getPosition(), width, height, maxForce, minSpeed, maxSpeed, strength, missileVelocity,
                target, movementBehaviour);
    }
}
