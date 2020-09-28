package game.model.ability;

import game.model.ILiving;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.movable.LivingEntity;
import game.model.entity.player.Player;
import game.model.level.ILevel;

import java.util.ArrayList;
import java.util.List;

public class Shockwave extends Ability{
    private double radius;
    private double force;

    public Shockwave(long cooldown, double radius, double force, LivingEntity<?> user) {
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
                List<Enemy> enemies = level.getEnemies();
                Player player = level.getPlayer();
                List<LivingEntity<?>> entities = new ArrayList<>(enemies);
                entities.add(player);

                for(LivingEntity<?> entity : entities){
                    if (user != entity) {
                        // Todo
                    }
                }

            }
        };
    }
}
