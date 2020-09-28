package model.ability;

import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.services.EntityFactory;
import org.junit.Test;

public class ShockwaveTest {
    IPlayer player = EntityFactory.basicPlayer(500, 500);

    IEnemy enemy1 = EntityFactory.basicEnemy(800,800, player, 10);
    IEnemy enemy2 = EntityFactory.basicEnemy(300, 300, player, 10);

    @Test
    public void EnemyPushed (){

    }
}
