package game.model.ability;

import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Missile;
import javafx.geometry.Point2D;

// Ability for shooting missiles
public class ShootMissile extends Shoot {
    private final double width;     // Width of missile
    private final double height;    // Height of missile
    private final double maxForce;  // Max force (responsiveness) of missile
    private final double maxSpeed;  // Max speed of missile
    private final double minSpeed;  // Minimum speed of missile. This ensures the missile never completely stops, which creates
                                    // a more realistic behavior.
    private final int strength;     // The strength of the missile
    private final IMovementBehaviour movementBehaviour; // The movement behavior of the missile. Usually a seeking behavior.

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
