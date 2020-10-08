package game.model.ability;

import game.model.ability.action.AbilityAction;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import game.model.entity.player.IPlayer;
import game.model.level.ILevel;

import game.util.Utils;
import javafx.geometry.Point2D;

public class Dash extends Ability {

    public static class DashAction extends AbilityAction {
        private Point2D dir = null;
        private final LivingEntity<?> user;

        public DashAction(IEntity<?> user) {
            super(user, 0.05);
            this.user = (LivingEntity<?>)user;
        }

        @Override
        public void apply(ILevel level, double timePassed) {

            if (dir == null) {
                user.setIsInvulnerable(true);
                user.setMaxSpeed(user.getMaxSpeed()*5);
                dir = user.getVelocity().multiply(user.getMaxSpeed());
                user.setVelocity(dir);
            }

        }

        @Override
        public void onFinished(ILevel level) {
            user.setMaxSpeed(user.getMaxSpeed() / 5);
            user.setIsInvulnerable(false);
        }
    };

    public Dash(long cooldown) {
        super(cooldown);
    }

    @Override
    protected IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
        return new DashAction(user);
    }
}
