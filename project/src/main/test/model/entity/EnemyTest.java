package model.entity;

import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyTest {
    private Enemy enemy;
    private IEntity<?> target;

    @Before
    public void setup() {
        enemy = new Enemy(new Point2D(0,0), 10, 2, 2, 1, null, null, null);
        target = new Player(new Point2D(0,0), 10, 2, 2);
    }

    @Test
    public void testSetTarget() {
        assertTrue(enemy.setTarget(target));
    }

    @Test
    public void testSetSelfTarget() {
        assertFalse(enemy.setTarget(enemy));
    }


}
