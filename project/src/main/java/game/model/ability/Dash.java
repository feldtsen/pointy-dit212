package game.model.ability;

import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.player.IPlayer;
import game.model.level.ILevel;

import javafx.geometry.Point2D;

public class Dash extends Ability {

    public Dash(long cooldown) {
        super(cooldown);
    }

    @Override
    protected IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
        // Anonymous implementation of an ability action is created on each use.
        return new IAbilityAction() {
            @Override
            public double getDuration() {
                return 0;
            }

            @Override
            public void apply(ILevel level, double timePassed) {
                IPlayer player = level.getPlayer();
                // Sets the distance dashed proportional to the size of the level.
                double distance = Math.sqrt(Math.pow(level.getHeight(),2) + Math.pow(level.getWidth(),2)) / 3;
                // Moves the player the distance in the direction it's currently facing.
                player.setPosition(player.getPosition().add(new Point2D(distance*Math.cos(player.getShape().getRotation()),distance*Math.sin(player.getShape().getRotation()))));
            }

            @Override
            public void onFinished(ILevel level) {
            }
        };
    }
}
