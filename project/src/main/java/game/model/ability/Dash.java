/*
 * Authors: Erik Magnusson, Anton Hildingsson, Mattias Oom
 */

package game.model.ability;

import game.model.ability.action.AbilityAction;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import game.model.level.ILevel;

import game.util.Utils;
import javafx.geometry.Point2D;

// An ability which causes the user to "dash" forward in the direction it is moving (or facing, if the entity is not moving)
public class Dash extends Ability {
    // The length of the dash. A bit arbitrary, since this depends on the duration
    private final double length;
    private final double maxSpeedMultiplier;

    // The ability action which the dash ability returns.
    public static class DashAction extends AbilityAction {
        // The direction of the dash. This is stored the first time the dash ability action is applied, and saved to
        // ensure the direction is kept consistent.
        private Point2D dir = null;

        // The user of the dash.
        private final LivingEntity<?> user;

        // The length of the dash.
        private final double length;

        // A multiplier for the max speed of the user. The max speed will be multiplied with this value on dash,
        // and set back to the regular value when the dash is finished
        private final double maxSpeedMultiplier;

        public DashAction(IEntity<?> user, double length, double duration, double maxSpeedMultiplier) {
            super(user, duration);
            this.user = (LivingEntity<?>)user;
            this.length = length;
            this.maxSpeedMultiplier = maxSpeedMultiplier;
        }

        @Override
        public void apply(ILevel level, double timePassed) {
            // First time the dash ability action is applied, the direction is updated
            if (dir == null) {
                // Set user to be invulnerable, to ensure it cannot be hit by projectiles or other entities during this time
                user.setIsInvulnerable(true);
                // Update user max speed
                user.setMaxSpeed(user.getMaxSpeed() * maxSpeedMultiplier);
                // If velocity is zero, set direction to the heading of the user
                if(user.getVelocity().getX() == 0 && user.getVelocity().getY() == 0){
                    dir = Utils.vectorFromHeading(user.getShape().getRotation(), length);
                // Otherwise, set direction to the direction of the velocity of the user
                } else {
                    dir = Utils.setMagnitude(user.getVelocity(), length);
                }
                // Update user velocity
                user.setVelocity(dir);
            }

        }

        @Override
        public void onFinished(ILevel level) {
            // When action is finished, set max speed back to normal and make entity vulnerable again
            user.setMaxSpeed(user.getMaxSpeed() / maxSpeedMultiplier);
            user.setIsInvulnerable(false);
        }
    };

    public Dash(long cooldown, double length, double speedMultiplier) {
        super(cooldown);
        this.length = length;
        this.maxSpeedMultiplier = speedMultiplier;
    }

    @Override
    protected IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
        return new DashAction(user, length, 0.1, maxSpeedMultiplier);
    }
}
