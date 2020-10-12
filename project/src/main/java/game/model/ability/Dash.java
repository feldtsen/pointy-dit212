package game.model.ability;

import game.model.ability.action.AbilityAction;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import game.model.level.ILevel;

import game.util.Utils;
import javafx.geometry.Point2D;

public class Dash extends Ability {


    private final double length;
    public static class DashAction extends AbilityAction {
        private Point2D dir = null;
        private final LivingEntity<?> user;
        private final double length;

        public DashAction(IEntity<?> user, double length) {
            super(user, 0.1);
            this.user = (LivingEntity<?>)user;
            this.length = length;
        }

        @Override
        public void apply(ILevel level, double timePassed) {

            if (dir == null) {
                user.setIsInvulnerable(true);
                user.setMaxSpeed(user.getMaxSpeed()*5);
                if(user.getVelocity().getX() == 0 && user.getVelocity().getY() == 0){
                    dir = Utils.vectorFromHeading(user.getShape().getRotation(), length);
                } else {
                    dir = Utils.setMagnitude(user.getVelocity(), length);
                }
                user.setVelocity(dir);
            }

        }

        @Override
        public void onFinished(ILevel level) {
            user.setMaxSpeed(user.getMaxSpeed() / 5);
            user.setIsInvulnerable(false);
        }
    };

    public Dash(long cooldown, double length) {
        super(cooldown);
        this.length = length;
    }

    @Override
    protected IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
        return new DashAction(user, length);
    }
}
