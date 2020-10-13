package services;

import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.level.ILevel;
import game.model.level.Level;
import game.services.LevelLoader;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;


// Test class for level loader.
// Level data is found in src/main/resources/levels/test.json - make sure data corresponds to test values.
public class LevelLoaderTest {
    
    ILevel level;
    
    @Before
    public void init() throws FileNotFoundException {
        level = LevelLoader.load("test");
    }

    @Test
    // Test to see if the lists of entities correspond to the number in the level file
    public void entityListProperties() {
        assertTrue(level.getObstacles().size() == 2);
        assertTrue(level.getEnemies().size() == 3);
        assertTrue(level.getProjectiles().isEmpty());
    }

    @Test
    public void playerPositionTest() {
        double x = 500;
        double y = 500;
        assertTrue(level.getPlayer().getPosition().getX() == x);
        assertTrue(level.getPlayer().getPosition().getX() == y);
    }

    @Test
    public void enemyPositionTest() {
        double x = 100;
        double y = 100;
        for (IEnemy e : level.getEnemies()) {
            assertTrue(e.getPosition().getX() == x);
            assertTrue(e.getPosition().getY() == y);
            x += 100;
            y += 100;
        }
    }

    @Test
    public void obstaclePositionTest() {
        double x = 300;
        double y = 300;
        for (IObstacle o : level.getObstacles()) {
            assertTrue(o.getPosition().getX() == x);
            assertTrue(o.getPosition().getY() == y);
            x += 300;
            y += 300;
        }
    }

    @Test
    public void selectEnemyTest() {
        IEnemy e = LevelLoader.selectEnemy(0,0,null,1,"null");
        try {
            assertTrue(e.equals(null));
        }
        catch (NullPointerException n) {
            assertTrue(true);
        }

        e = LevelLoader.selectEnemy(0,0,null,1,"basic");
        assertFalse(e.equals(null));

        e = LevelLoader.selectEnemy(0,0,null,1,"bullet");
        assertFalse(e.equals(null));

        e = LevelLoader.selectEnemy(0,0,null,1,"missile");
        assertFalse(e.equals(null));
    }








    

}
