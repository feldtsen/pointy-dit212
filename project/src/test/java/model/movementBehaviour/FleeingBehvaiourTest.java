package model.movementBehaviour;

import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.services.EntityFactory;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FleeingBehvaiourTest {
    /*TODO: test that placing an enemy too close to a target makes it move away.
            test that placing an enemy too far away from a target makes it move closer.
            test that placing an enemy in the "middle" makes it strafe
     */
    private IEnemy enemy;
    private IPlayer player;


    private void setup(int enemyX, int enemyY) {
        player = EntityFactory.basicPlayer(0, 0);
        enemy = EntityFactory.bulletEnemy(enemyX, enemyY, player, 1);
    }


    @Test
    // Tests that an enemy, with the fleeing behaviour, will flee if too close to its target.
    public void testFleeing() {

        // Places the enemy too close to its target.
        setup(100, 0);

        // Distance between entities at the start.
        double dist1 = player.getPosition().distance(enemy.getPosition());

        enemy.update(0.1, 1);

        // Distance after the enemy has moved.
        double dist2 = player.getPosition().distance(enemy.getPosition());

        // If the enemy has begun to flee, the second distance should be larger than the first one.
        assertTrue(dist2 > dist1);
    }

    @Test
    // An enemy that is too far from its target should be moving closer.
    public void testApproaching() {
        // Places the enemy too far from the target.
        setup(600, 0);

        double dist1 = player.getPosition().distance(enemy.getPosition());

        enemy.update(0.1, 1);

        double dist2 = player.getPosition().distance(enemy.getPosition());

        // The enemy is approaching if the second distance is smaller than the first.
        assertTrue(dist1 > dist2);
    }

    @Test
    // If the enemy is neither too far away nor to close, it should be moving sideways.
    public void testStrafe() {
        // Places the enemy at a distance from the player where it should strafe.
        setup(400, 0);

        // Vector pointing from enemy to target.
        Point2D difVector = player.getPosition().subtract(enemy.getPosition());

        enemy.update(0.1, 1);


        // The enemy is strafing if its velocity vector is orthogonal to the original vector from the enemy to the player.
        assertTrue(difVector.dotProduct(enemy.getVelocity()) == 0);
    }
}
