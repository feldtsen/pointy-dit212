package services;

import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.level.ILevel;
import game.services.ILevelLoader;
import game.services.LevelLoader;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


// Test class for level loader.
// Level data is found in src/main/resources/levels/test.json - make sure data corresponds to test values.
public class LevelLoaderTest {

    ILevelLoader levelLoader;
    ILevel level;
    
    @Before
    public void init() {
        levelLoader = new LevelLoader("src/main/resources/game/levels/testlevels/LevelLoaderTest/");
        level = levelLoader.getLevel();
    }

    @Test
    // Test to see if the lists of entities correspond to the number in the level file
    public void entityListProperties() {
        assertEquals(2, level.getObstacles().size());
        assertEquals(3, level.getEnemies().size());
        assertTrue(level.getProjectiles().isEmpty());
    }


    @Test
    public void playerPositionTest() {
        double x = 500;
        double y = 500;
        assertEquals(level.getPlayer().getPosition().getX(), x, 0.0);
        assertEquals(level.getPlayer().getPosition().getX(), y, 0.0);
    }

    @Test
    public void enemyPositionTest() {
        double x = 100;
        double y = 100;
        for (IEnemy e : level.getEnemies()) {
            assertEquals(e.getPosition().getX(), x, 0.0);
            assertEquals(e.getPosition().getY(), y, 0.0);
            x += 100;
            y += 100;
        }
    }

    @Test
    public void obstaclePositionTest() {
        double x = 300;
        double y = 300;
        for (IObstacle o : level.getObstacles()) {
            assertEquals(o.getPosition().getX(), x, 0.0);
            assertEquals(o.getPosition().getY(), y, 0.0);
            x += 300;
            y += 300;
        }
    }

    @Test
    public void testHasNext() {
        assertFalse(levelLoader.hasNext());
    }












    

}
