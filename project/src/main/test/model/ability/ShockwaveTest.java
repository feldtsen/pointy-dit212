package model.ability;

import game.model.ability.IAbility;
import game.model.ability.Shockwave;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.services.EntityFactory;
import org.junit.Test;

public class ShockwaveTest {
    IPlayer player = EntityFactory.basicPlayer(500, 700);
    IAbility shockwave = new Shockwave(10, 100, 1500);

    IEnemy enemy1 = EntityFactory.basicEnemy(500,650, player, 10);
    IEnemy enemy2 = EntityFactory.basicEnemy(500, 750, player, 10);

    @Test
    public void EnemyPushed (){

    }
}
