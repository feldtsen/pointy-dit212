/*
 * Authors: Erik Magnusson, Anton Hildingsson, Mattias Oom, Joachim Pedersen
 *
 * Loads levels according to information stored in JSON-files. Acts as an iterator of levels for Game.
 */

package game.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import game.model.entity.IEntity;
import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.model.level.Level;
import game.view.ViewResourceLoader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// Class for loading levels using GSON JSON parser.
public class LevelLoader implements ILevelLoader {

    private final String path;
    private JsonObject levelJSON;
    private int levelNr;

    // Constructor takes the level ID in form of a string and parses the level data into a JSON object.
    public LevelLoader(String path)  {
        this.path = path;
        this.levelNr = 1;
    }

    // Get the level corresponding to levelNr.
    @Override
    public ILevel getLevel()  {
        String filePath = path + levelNr + ".json";
        try {
            levelJSON = new JsonParser().parse(new FileReader(filePath)).getAsJsonObject();

            // Loads the entities from the file
            List<IObstacle> obstacles = loadObstacles();
            IPlayer player = loadPlayer();
            List<IEnemy> enemies = loadEnemies(player);
            List<IProjectile<?>> projectiles = loadProjectiles();

            double LEVEL_WIDTH = ViewResourceLoader.INITIAL_WIDTH;
            double LEVEL_HEIGHT = ViewResourceLoader.INITIAL_HEIGHT;
            return new Level(levelNr, enemies, projectiles, obstacles, player, LEVEL_WIDTH, LEVEL_HEIGHT);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
    // Checks to see if there are any more levels to load in the folder.
    @Override
    public boolean hasNext() {
        levelNr += 1;
        if (getLevel() == null) {
            levelNr -= 1;
            return false;
        }
        levelNr -= 1;
        return true;
    }

    // Returns next level in the specified folder. Expects levels to be numbered "1, 2, ..., n"
    @Override
    public ILevel next() {
        levelNr += 1;
        return getLevel();
    }

    @Override
    public ILevel getLevel(int levelNr) {
        this.levelNr = levelNr;
        return getLevel();
    }

    // Loads the player according to the parameters in level file.
    private IPlayer loadPlayer() {
        double x = levelJSON.getAsJsonObject("Player").get("x").getAsDouble();
        double y = levelJSON.getAsJsonObject("Player").get("y").getAsDouble();
        return EntityFactory.basicPlayer(x,y);
    }

    // Loads the enemies from the level file into the game.
    // Reads the different parameters and calls method selectEnemies.
    // Uses player as the target.
    private List<IEnemy> loadEnemies(IPlayer player) {
        List<IEnemy> enemies = new ArrayList<>();
        JsonArray enemyArr = levelJSON.getAsJsonArray("Enemies");
        for (int i = 0; i < enemyArr.size(); i++) {
            String type = enemyArr.get(i).getAsJsonObject().get("type").getAsString();
            double x = enemyArr.get(i).getAsJsonObject().get("x").getAsDouble();
            double y = enemyArr.get(i).getAsJsonObject().get("y").getAsDouble();
            int difficulty = enemyArr.get(i).getAsJsonObject().get("difficulty").getAsInt();
            String spikeImmunity = enemyArr.get(i).getAsJsonObject().get("spikeimmune").getAsString();
            enemies.add(selectEnemy(x, y, player, difficulty, type, spikeImmunity));
        }
        return enemies;
    }

    // Loads the obstacles from the level file into the game.
    // Reads the different parameters and calls method selectObstacle
    private List<IObstacle> loadObstacles() {
        List<IObstacle> obstacles = new ArrayList<>();
        JsonArray obstacleArr = levelJSON.getAsJsonArray("Obstacles");
        for (int i = 0; i < obstacleArr.size(); i++) {
            String type = obstacleArr.get(i).getAsJsonObject().get("type").getAsString();
            double x1 = obstacleArr.get(i).getAsJsonObject().get("x1").getAsDouble();
            double y1 = obstacleArr.get(i).getAsJsonObject().get("y1").getAsDouble();
            double x2 = obstacleArr.get(i).getAsJsonObject().get("x2").getAsDouble();
            double y2 = obstacleArr.get(i).getAsJsonObject().get("y2").getAsDouble();
            double height = obstacleArr.get(i).getAsJsonObject().get("height").getAsDouble();
            double width = obstacleArr.get(i).getAsJsonObject().get("width").getAsDouble();

            obstacles.add(selectObstacle(x1, y1, x2, y2, width, height, type));
        }
        return obstacles;
    }

    //Returns an empty list since no projectiles are loaded at the start of a level.
    private List<IProjectile<?>> loadProjectiles() {
        return new ArrayList<>();
    }

    // Returns the type of enemy according to "type" specified in level file.
    // Uses EntityFactory
    private IEnemy selectEnemy(double x, double y, IEntity target, int difficulty, String type, String spikeImmunity) {
        IEnemy enemy = null;
        // Returns basic enemy without abilities
        switch (type) {
            case "basic":
                enemy = EntityFactory.basicEnemy(x, y, target, difficulty);
                break;
            // Returns enemy which fires bullets
            case "bullet":
                enemy = EntityFactory.bulletEnemy(x, y, target, difficulty);
                break;
            // Returns enemy which fires homing missiles
            case "missile":
                enemy = EntityFactory.missileEnemy(x, y, target, difficulty);
                break;
        }
        if (spikeImmunity.equals("true")) {
            assert enemy != null;
            enemy.setStrength(101);
        }
        return enemy;
    }

    // Returns the type of obstacle according to "type" specified in level file.
    // Uses EntityFactory
    private IObstacle selectObstacle(double x1, double y1, double x2, double y2, double width, double height, String type) {
        IObstacle obstacle = null;
        // Stationary obstacle
        switch (type) {
            case "wall":
                obstacle = EntityFactory.wall(x1, y1, width, height);
                break;
            // Obstacle that moves across the level. Moves between points (x1,y1) and (x2,y2)
            case "moving":
                obstacle = EntityFactory.movingWall(x1, y1, x2, y2, width, height);
                break;
            // Spike obstacle which can kill other entities
            case "spikes":
                obstacle = EntityFactory.spikes(x1, y1, width, height);
                break;
        }
        return obstacle;
    }


}
