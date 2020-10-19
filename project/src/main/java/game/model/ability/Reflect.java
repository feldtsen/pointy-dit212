/*
 * Authors: Anton Hildingsson, Mattias Oom
 */

package game.model.ability;

import game.model.ability.action.AbilityAction;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.util.Utils;
import javafx.geometry.Point2D;

// Ability for reflecting projectiles
public class Reflect extends Ability {
    // Max/min angle of affected projectiles, relative to user
    private final double angle;

    // Distance or reach
    private final double radius;

    // How strongly reflected projectiles are affected
    private final double control;

    // How long the ability is active
    private final double duration;

    // The strength of the reflected projectiles
    private final int strength;

    // Action to be returned when reflect ability is applied
    public static class ReflectAction extends AbilityAction {
        private final double angle;
        private final double radius;
        private final double control;
        private final int strength;

        public ReflectAction(IEntity<?> user, double duration, double radius, double angle, double control, int strength) {
            super(user, duration);
            this.radius = radius;
            this.angle = angle;
            this.control = control;
            this.strength = strength;
        }

        @Override
        public void apply(ILevel level, double timePassed) {
            // Loop over all the projectiles in the current level
            for(IProjectile<?> projectile : level.getProjectiles()) {
                // Calculate a vector pointing from the user to the projectile
                Point2D vector = projectile.getPosition().subtract(user.getPosition());

                // Check if the projectile is outside the reach of radius
                if(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) > radius * radius) continue;

                // Calculate the difference in the angle representing the facing direction of the user and the angle
                // pointing to the projectile, from the user
                double relativeAngle = Utils.limitAngle(user.getShape().getRotation() - Utils.heading(vector));

                // If the relative angle is less than half of the reflection angle, or greater than
                // the negative of half of the reflection angle (mod two PI), keep going
                if(!(relativeAngle < angle / 2 || relativeAngle > Math.PI * 2 - angle / 2)) continue;

                // Calculate unit vector pointing from user to projectile
                Point2D n = vector.normalize();
                // Calculate vector representing user facing direction
                Point2D f = Utils.vectorFromHeading(user.getShape().getRotation(), 1);

                // Interpolate to get direction of new velocity
                Point2D velocityDirection = n.interpolate(f, control);

                // Calculate and set new velocity
                Point2D velocity = Utils.setMagnitude(velocityDirection, projectile.getVelocity().magnitude());
                projectile.setVelocity(velocity);

                // Update projectile strength. Projectiles are typically made to be stronger
                // on reflect. This makes sure they can hurt other entities than the player, which
                // typically possesses the reflect ability.
                projectile.setStrength(this.strength);
            }
        }

        @Override
        public void onFinished(ILevel level) {
            // Do nothing
        }
    }

    public Reflect(long cooldown, double angle, double radius, double control, double duration, int strength) {
        super(cooldown);
        // Throw exception if control is less than 0 or greater than 1
        if(control < 0 || control > 1) throw new IllegalArgumentException();
        this.angle = angle;
        this.radius = radius;
        this.control = control;
        this.duration = duration;
        this.strength = strength;
    }

    @Override
    protected IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
        return new ReflectAction(user, duration, radius, angle, control, strength);
    }
}
