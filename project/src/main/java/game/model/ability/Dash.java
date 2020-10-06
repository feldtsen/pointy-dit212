package game.model.ability;

import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.player.IPlayer;
import game.model.level.ILevel;

import game.util.Utils;
import javafx.geometry.Point2D;

public class Dash extends Ability {

    public Dash(long cooldown) {
        super(cooldown);
    }

    @Override
    protected IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
        // Anonymous implementation of an ability action is created on each use.
        IPlayer player = (IPlayer) user;
        player.setIsInvulnerable(true);


        return new IAbilityAction() {

            Point2D dir = null;

            @Override
            public double getDuration() {
                return 0.05;
            }

            @Override
            public void apply(ILevel level, double timePassed) {

                if (dir == null) {
                    player.setMaxSpeed(player.getMaxSpeed()*2);
                    dir = Utils.vectorFromHeading(player.getShape().getRotation(), player.getMaxSpeed());
                }
                System.out.println(dir);
                player.setVelocity(dir);
            }

            @Override
            public void onFinished(ILevel level) {
            }
        };
    }
}
