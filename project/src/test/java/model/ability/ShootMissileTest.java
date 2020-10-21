package model.ability;

import game.model.ability.action.IAbilityAction;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.Missile;
import game.model.level.ILevel;
import game.model.level.Level;
import game.services.EntityFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShootMissileTest {
    private IEnemy user;
    private IPlayer target;
    private ILevel level;

    private void init() {
        target = EntityFactory.basicPlayer(100, 100);
        user = EntityFactory.missileEnemy(0, 0, target, 1);

        List<IEnemy> enemies = new ArrayList<>();
        enemies.add(user);

        level = new Level(0, enemies, new ArrayList<>(), new ArrayList<>(), target, 100000, 100000);
    }

    @Test
    public void testCreateMissile() {
        init();

        // Initially the number of projectiles should be 0.
        assertTrue(level.getProjectiles().size() == 0);

        // Apply ability (create missile).
        IAbilityAction abilityAction = user.applyAbility();
        abilityAction.apply(level, 0);
        abilityAction.onFinished(level);

        // THe number of projectiles should now be 1 and that projectile should be a missile.
        assertTrue(level.getProjectiles().size() == 1 && level.getProjectiles().get(0) instanceof Missile);
    }
}
