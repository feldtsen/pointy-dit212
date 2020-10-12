package model.ability;

import game.model.Game;
import game.model.ability.Dash;
import game.model.ability.IAbility;
import game.model.ability.Shockwave;
import game.model.ability.action.IAbilityAction;
import game.model.entity.Entity;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.model.level.ILevel;
import game.model.level.Level;
import game.services.EntityFactory;
import game.util.Utils;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class DashTest {

    ILevel level;
    IPlayer player;
    IEnemy enemy;

    double length = 200;


    // TODO: Maybe change implementation of dash before creating more tests
    @Before
    public void before(){
        player = EntityFactory.basicPlayer(500, 500);
        enemy = EntityFactory.basicEnemy(550,500,player , 5);
        IAbility dash = new Dash(10,length);
        player.addAbility(dash);

        //Vector between player and enemy, used to make the player face the enemy
        Point2D vector = enemy.getPosition().subtract(player.getPosition());
        double dir = Utils.heading(vector);
        player.getShape().setRotation(dir);


    }

    private void dash(){
        IAbilityAction abilityAction = player.activateAbility(0);
        abilityAction.apply(level,0);
        abilityAction.onFinished(level);
    }

    @Test
    public void testDashWhileMoving(){

        player.setVelocity(new Point2D(100,0));
        Point2D newVelocity = Utils.setMagnitude(player.getVelocity(), length);
        double oldMaxSpeed = player.getMaxSpeed();

        dash();

        assertEquals(newVelocity.getX(), player.getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), player.getVelocity().getY(),0.0);
        assertEquals(oldMaxSpeed, player.getMaxSpeed(),0.0);

    }


}
