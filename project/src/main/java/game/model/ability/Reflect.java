package game.model.ability;

import game.model.ability.action.AbilityAction;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.util.Utils;
import javafx.geometry.Point2D;

public class Reflect extends Ability {
    private final double angle;    // Max/min angle of affected projectiles, relative to user
    private final double radius;   // Distance or reach
    private final double control;    // How strongly reflected projectiles are affected
    private final double duration; // How long the ability is active
    private final int strength; // The strenght of the reflected projectiles

    public static class ReflectAction extends AbilityAction {
        private final double radius;
        private final double angle;
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

                // Check if the angle between the facing direction of the user and the projectile is too large
                double relativeAngle = Utils.limitAngle(user.getShape().getRotation() - Utils.heading(vector));

                if(!(relativeAngle < angle / 2 || relativeAngle > Math.PI * 2 - angle / 2)) continue;

                //if(relativeAngle > angle / 2 && Math.PI * 2 - relativeAngle < Math.PI * 2 - angle/2) continue;

                // Calculate unit vector pointing from user to projectile
                Point2D n = vector.normalize();
                // Calculate vector representing user facing direction
                Point2D f = Utils.vectorFromHeading(user.getShape().getRotation(), 1);

                // Interpolate to get direction of new velocity
                Point2D velocityDirection = n.interpolate(f, control);

                // Calculate and set new velocity
                Point2D velocity = Utils.setMagnitude(velocityDirection, projectile.getVelocity().magnitude());
                projectile.setVelocity(velocity);

                projectile.setStrength(this.strength);
            }
        }

        @Override
        public void onFinished(ILevel level) {
        }


    }

    public Reflect(long cooldown, double angle, double radius, double control, double duration, int strength) {
        super(cooldown);
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
