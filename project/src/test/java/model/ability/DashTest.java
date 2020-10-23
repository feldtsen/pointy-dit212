package model.ability;

import game.model.ability.Dash;
import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.model.entity.player.Player;
import game.model.level.ILevel;
import game.services.EntityFactory;
import game.util.Utils;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class DashTest {

    ILevel level;
    IPlayer player;
    IEnemy enemy;

    final double length = 3000;


    // TODO: Maybe change implementation of dash before creating more tests
    @Before
    public void before(){

        player = new Player(new Point2D(500, 500), 20, 2500, 1000,0);

        enemy = EntityFactory.basicEnemy(550,500,player , 5);
        IAbility dash = new Dash(10, length, 5);
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
    public void testDashWhileMovingX(){

        player.setVelocity(new Point2D(100,0));
        Point2D newVelocity = Utils.setMagnitude(player.getVelocity(), length);
        double oldMaxSpeed = player.getMaxSpeed();

        dash();

        assertEquals(newVelocity.getX(), player.getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), player.getVelocity().getY(),0.0);
        assertEquals(oldMaxSpeed, player.getMaxSpeed(),0.0);

    }

    @Test
    public void testDashWhileMovingY(){

        player.setVelocity(new Point2D(0,100));
        Point2D newVelocity = Utils.setMagnitude(player.getVelocity(), length);
        double oldMaxSpeed = player.getMaxSpeed();

        dash();

        assertEquals(newVelocity.getX(), player.getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), player.getVelocity().getY(),0.0);
        assertEquals(oldMaxSpeed, player.getMaxSpeed(),0.0);

    }
    @Test
    public void testDashWhileNotMoving(){

        Point2D newVelocity = Utils.vectorFromHeading(player.getShape().getRotation(), length);
        double oldMaxSpeed = player.getMaxSpeed();

        dash();

        assertEquals(newVelocity.getX(), player.getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), player.getVelocity().getY(),0.0);
        assertEquals(oldMaxSpeed, player.getMaxSpeed(),0.0);

    }

    @Test
    public void testDashTwice(){

        Point2D newVelocity = Utils.vectorFromHeading(player.getShape().getRotation(), length);
        double oldMaxSpeed = player.getMaxSpeed();
        IAbilityAction abilityAction = player.activateAbility(0);
        abilityAction.apply(level,0);
        abilityAction.apply(level,0);
        abilityAction.onFinished(level);
        assertEquals(newVelocity.getX(), player.getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), player.getVelocity().getY(),0.0);
        assertEquals(oldMaxSpeed, player.getMaxSpeed(),0.0);
    }

}
