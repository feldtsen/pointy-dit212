package model.ability;

import game.model.ability.IAbility;
import game.model.ability.ShootBullet;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.AbilityBehaviour;
import game.model.behavior.ability.IAbilityBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.Player;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.model.level.Level;
import javafx.geometry.Point2D;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShootBulletTest {
    IEnemy user;
    IEntity<?> target;
    ILevel level;
    IAbilityBehaviour abilityBehaviour;

    //Create AbilityBehaviour with dummy apply method
    //Create enemy, add abilityBehaviour with ShootBullet
    public void init() {

        // Create ability ShootBullet
        IAbility ability = new ShootBullet(0, 0.1, 3, 5, 1);

        // Create list of abilities for AbilityBehaviour
        List<IAbility> abilities = new ArrayList<>();
        abilities.add(ability);

        // Creates abilityBehaviour and adds list containing ShootBullet.
        abilityBehaviour = new AbilityBehaviour(abilities) {
            @Override
            // Dummy method. Use first ability in list.
            public IAbilityAction apply(IEntity<?> user, IEntity<?> target) {

                // Return abilityAction from ability
                return abilities.get(0).use(user, target);
            }
        };

        // Initialize user and target to two Enemies.
        target = new Player(new Point2D(1, 0), 1, 1, 3,0);
        user = new Enemy(new Point2D(0, 0), 1, 3, 3, 3,3,  abilityBehaviour, null, target);

        // Create new level with list for projectiles.
        level = new Level(0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, 100, 100);
    }

    @Test
    // Test if using the ability leads to a projectile being added.
    public void testShoot() {
        init();
        shoot();

        assertEquals(1, level.getProjectiles().size());
    }

    @Test
    public void testBulletVelocity() {
        init();
        shoot();
        IProjectile<?> bullet = level.getProjectiles().get(0);
        assertEquals(5, bullet.getVelocity().magnitude(), 0.0);
    }

    private void shoot() {
        IAbilityAction abilityAction = user.applyAbility();
        abilityAction.apply(level, 0);
        abilityAction.onFinished(level);
    }
}
