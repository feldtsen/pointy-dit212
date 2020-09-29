package game.model.ability;

import game.model.IMovable;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.movable.LivingEntity;
import game.model.entity.player.IPlayer;
import game.model.entity.player.Player;
import game.model.level.ILevel;
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Shockwave extends Ability{
    private final double radius;
    private final double force;

    public Shockwave(long cooldown, double radius, double force) {
        super(cooldown);
        this.radius = radius;
        this.force = force;
    }

    public IAbilityAction createAction(IEntity<?> user, IEntity<?> target){
        return new IAbilityAction() {
            @Override
            public double getDuration() {
                return 0;
            }

            @Override
            public void apply(ILevel level, double timePassed) {
                List<IEnemy> enemies = level.getEnemies();
                IPlayer player = level.getPlayer();
                List<IMovable<?>> entities = new ArrayList<>(enemies);
                entities.add(player);

                for(IMovable<?> entity : entities){
                    if (user == entity) {
                        continue;
                    }
                    Point2D v = entity.getPosition().subtract(user.getPosition());
                    if (Math.pow(v.getX(),2)+ Math.pow(v.getY(),2) <= Math.pow(radius,2)){
                        double distance = v.magnitude();
                        double power = 1 - distance/radius;

                        Point2D acceleration = Utils.setMagnitude(v, power * force);

                        entity.addForce(acceleration);

                    }
                }

            }
        };
    }
}
