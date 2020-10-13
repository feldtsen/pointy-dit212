package model.ability;

import game.model.ability.IAbility;
import game.model.ability.Shockwave;
import game.model.ability.action.IAbilityAction;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.model.entity.player.Player;
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

public class ShockwaveTest {

    ILevel level;
    IPlayer player;
    IEnemy enemy;
    double radius = 100;
    double force;

    @Before
    public void before (){
        player = new Player(new Point2D(500, 500), 20, 2500, 1000,0);

        enemy = EntityFactory.basicEnemy(500,550, player, 10);

        List<IEnemy> enemies = new ArrayList<>(List.of(enemy));


        level = new Level(enemies,null,null, player, 1200, 800);
    }

    @Test
    public void EnemyPushed (){
        force = 1500;
        testSetup(radius, force);

        // Create vector pointing from user to target entity
        Point2D vector = enemy.getPosition().subtract(player.getPosition());
        Point2D expectedAcceleration = newEnemyAcceleration(vector);

        shockwave();

        assertEquals(expectedAcceleration.getX(), enemy.getAcceleration().getX(),0.0);
        assertEquals(expectedAcceleration.getY(), enemy.getAcceleration().getY(),0.0);

    }

    @Test
    public void EnemyOutOfRange (){
        force = 1500;
        testSetup(radius, force);
        enemy.setPosition(new Point2D(500,601));
        // Create vector pointing from user to target entity
        Point2D vector = enemy.getPosition().subtract(player.getPosition());
        double expectedAccelerationX = enemy.getAcceleration().getX();
        double expectedAccelerationY = enemy.getAcceleration().getY();
        shockwave();

        assertEquals(expectedAccelerationX, enemy.getAcceleration().getX(),0.0);
        assertEquals(expectedAccelerationY, enemy.getAcceleration().getY(),0.0);

    }
    private void shockwave(){
        IAbilityAction abilityAction = player.activateAbility(0);
        abilityAction.apply(level, 0);
        abilityAction.onFinished(level);
    }

    private Point2D newEnemyAcceleration(Point2D vector){

        // Calculate the distance between the user and the target
        double distance = vector.magnitude();

        // Calculate "power", signifying how effective the ability will be.
        // Power will approach 1 as the target's distance to the user approaches 0.
        double power = 1 - distance/radius;

        // Create an acceleration vector which will be applied to the target.
        return Utils.setMagnitude(vector, power * force);
    }

    private void testSetup(double radius, double force){

        IAbility shockwave = new Shockwave(10, radius, force, 0.1);
        player.addAbility(shockwave);
    }
}
