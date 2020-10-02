package services;

import game.model.level.Level;
import game.services.LevelLoader;
import org.junit.Test;

import java.io.FileNotFoundException;

public class LevelLoaderTest {

    @Test
    public void testLevelLoader() throws FileNotFoundException {
        Level level = LevelLoader.load("testLevel");

        //TODO: Make proper test

        System.out.println(level.getWidth());
        System.out.println(level.getHeight());
        System.out.println(level.getEnemies());
        System.out.println(level.getObstacles());
        System.out.println(level.getPlayer());
        System.out.println(level.getProjectiles());
    }

}
