package model.ability;

import game.model.ability.IAbility;
import game.model.ability.Shockwave;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.model.entity.player.Player;
import game.model.level.ILevel;
import game.model.level.Level;
import game.services.EntityFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShockwaveTest {

    Player player = EntityFactory.basicPlayer(500, 700);
    IAbility shockwave = new Shockwave(10, 100, 1500, 0.1);

    Enemy enemy1 = EntityFactory.basicEnemy(500,650, player, 10);
    Enemy enemy2 = EntityFactory.basicEnemy(500, 750, player, 10);

    List<Enemy> enemies = new ArrayList<>(List.of(enemy1, enemy2));

    ILevel level = new Level(enemies,null,null, player, 1200, 800);

    @Before
    public void before (){
        player.addAbility(shockwave);
    }

    @Test
    public void EnemyPushed (){
        player.activateAbility(0);
    }
}
