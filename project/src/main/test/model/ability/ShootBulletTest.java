package model.ability;

import game.model.ability.Ability;
import game.model.ability.IAbility;
import game.model.ability.ShootBullet;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.AbilityBehaviour;
import game.model.behavior.ability.IAbilityBehaviour;
import game.model.entity.Entity;
import game.model.entity.enemy.Enemy;
import game.model.entity.movable.LivingEntity;
import game.model.entity.movable.MovableEntity;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.Player;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.model.level.Level;
import game.model.shape2d.Circle;
import javafx.geometry.Point2D;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/*
    TODO:
        PROBLEM:
         ShootBullet takes a target and user as constructor arguments. ShootBullet needs to be created after
         user.
         Enemy(user) needs to have an IAbilityBehaviour in Constructor -> needs Ability (ShootBullet).
         Enemy needs to be created after ShootBullet.
         Which should be created first and how???
         Attempts:
         Creating setters in ShootBullet doesn't fix constructor issue, since anonymous AbilityAction wouldn't
         be affected.
         Brainstorm solutions:
         Give IAbilityBehaviour setters for Abilities so that an enemy can get created without needing to
         initialize Abilities

 */

public class ShootBulletTest {
    Enemy user;
    Enemy target;
    ILevel level;

    //Create AbilityBehaviour with dummy apply method
    //Create enemy, add abilityBehaviour
    //Create ShootBullet and add to abilityBehaviour
    public void init() {
        // Creates abilityBehaviour without adding any abilities
        IAbilityBehaviour abilityBehaviour = new AbilityBehaviour() {
            @Override
            // Dummy method. If there is an ability in list, use first one.
            public IAbilityAction apply(ILevel level) {
                List<IAbility> abilities = getAbilities();
                if (abilities == null) return null; // If apply is called before abilities are added.

                // Return abilityAction from ability
                return abilities.get(0).use();
            }
        };

        target = new Enemy(new Point2D(1, 0), 1, 3, 3, 3, 4, null, null, null);
        user = new Enemy(new Point2D(0, 0), 1, 3, 3, 3,3,  abilityBehaviour, null, target);

        List<IAbility> abilities = new ArrayList<>();
        abilities.add(0, new ShootBullet(user, target, 3, 0.1, 5, 5, 1));
        abilityBehaviour.setAbilities(abilities);
    }


}
