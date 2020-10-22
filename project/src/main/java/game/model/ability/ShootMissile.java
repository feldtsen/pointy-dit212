/*
 * Authors: Anton Hildingsson, Simon Genne, Joachim Pedersen
 *
 * Ability for shooting missiles. This ability is typically used by enemies,
 * with the player as target.
 */

package game.model.ability;

import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Missile;
import javafx.geometry.Point2D;

public class ShootMissile extends Shoot {
    // Width of missile
    private final double width;

    // Height of missile
    private final double height;

    // Max force (responsiveness) of missile
    private final double maxForce;

    // Max speed of missile
    private final double maxSpeed;

    // Minimum speed of missile. This ensures the missile never completely stops, which creates
    // a more realistic behavior.
    private final double minSpeed;

    // The strength of the missile
    private final int strength;

    // The movement behavior of the missile. Usually a seeking behavior.
    private final IMovementBehaviour movementBehaviour;

    public ShootMissile(long cooldown, double width, double height, double maxForce, double maxSpeed, double minSpeed,
                        int strength, IMovementBehaviour movementBehaviour) {
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
