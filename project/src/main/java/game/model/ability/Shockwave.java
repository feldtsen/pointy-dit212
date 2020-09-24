package game.model.ability;

import game.model.ILiving;
import game.model.ability.action.IAbilityAction;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.movable.LivingEntity;
import game.model.entity.player.Player;
import game.model.level.ILevel;

import java.util.ArrayList;
import java.util.List;


public class Shockwave extends Ability{

    private final double radius;
    private final double force;
    private final LivingEntity user;

    public Shockwave(long cooldown, double radius, double force, LivingEntity user) {
        super(cooldown, createAction(radius,force, user));
        this.radius = radius;
        this.force = force;
        this.user = user;
    }
    private static IAbilityAction createAction(double radius, double force, LivingEntity user){
        return new IAbilityAction() {
            @Override
            public double getDuration() {
                return 0;
            }

            @Override
            public void apply(ILevel level, double timePassed) {
                List<Enemy> enemies = level.getEnemies();
                Player player = level.getPlayer();
                List<LivingEntity<?>> entities = new ArrayList<>();
                entities.addAll(enemies);
                entities.add(player);

                for(LivingEntity entity : entities){
                    if (user != entity) {
                        // Todo
                    }
                }

            }
        };
    }
    public double getRadius(){return radius;}
    public double getForce(){return force;}
}
