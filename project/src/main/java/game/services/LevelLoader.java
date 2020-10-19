/*
 * Authors: Erik Magnusson, Anton Hildingsson, Mattias Oom, Joachim Pedersen
 */

package game.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import game.model.entity.Entity;
import game.model.entity.IEntity;
import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.obstacle.MovingWall;
import game.model.entity.obstacle.Wall;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;
import game.model.level.Level;
import javafx.geometry.Point2D;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// Class for loading levels using GSON JSON parser.
public class LevelLoader {
    private static JsonObject levelJSON;

    // Constructor takes the level ID in form of a string and parses the level data into a JSON object.
    public static Level load(String levelID) throws FileNotFoundException {
        levelJSON = new JsonParser().parse(new FileReader("src/main/resources/game/levels/" + levelID + ".json")).getAsJsonObject();

        List<IObstacle> obstacles = loadObstacles();
        IPlayer player = loadPlayer();
        List<IEnemy> enemies = loadEnemies(player);
        List<IProjectile<?>> projectiles = loadProjectiles();

        return new Level(enemies, projectiles, obstacles, player, 1200, 675);
    }

    // Returns the IDs of the next level
    public static List<String> getLevelIDs(int nrOfLevels) {
        List<String> id = new ArrayList<>();
        for (int i = 1; i <= nrOfLevels; i++) {
            id.add(Integer.toString(i));
        }
        return id;
    }

    // Loads the player according to the parameters in level file.
    private static IPlayer loadPlayer() {
        double x = levelJSON.getAsJsonObject("Player").get("x").getAsDouble();
        double y = levelJSON.getAsJsonObject("Player").get("y").getAsDouble();
        return EntityFactory.basicPlayer(x,y);
    }

    // Loads the enemies from the level file into the game.
    // Reads the different parameters and calls method selectEnemies.
    // Uses player as the target.
    private static List<IEnemy> loadEnemies(IPlayer player) {
        List<IEnemy> enemies = new ArrayList<>();
        JsonArray enemyArr = levelJSON.getAsJsonArray("Enemies");
        for (int i = 0; i < enemyArr.size(); i++) {
            String type = enemyArr.get(i).getAsJsonObject().get("type").getAsString();
            double x = enemyArr.get(i).getAsJsonObject().get("x").getAsDouble();
            double y = enemyArr.get(i).getAsJsonObject().get("y").getAsDouble();
            int difficulty = enemyArr.get(i).getAsJsonObject().get("difficulty").getAsInt();
            enemies.add(selectEnemy(x, y, player, difficulty, type));
        }
        return enemies;
    }

    // Loads the obstacles from the level file into the game.
    // Reads the different parameters and calls method selectObstacle
    private static List<IObstacle> loadObstacles() {
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
    private static List<IProjectile<?>> loadProjectiles() {
        return new ArrayList<>();
    }

    // Returns the type of enemy according to "type" specified in level file.
    // Uses EntityFactory
    public static IEnemy selectEnemy(double x, double y, IEntity target, int difficulty, String type) {
        IEnemy enemy = null;
        // Returns basic enemy without abilities
        if (type.equals("basic")) {
            enemy = EntityFactory.basicEnemy(x, y,target, difficulty);
        }
        // Returns enemy which fires bullets
        else if (type.equals("bullet")) {
            enemy = EntityFactory.bulletEnemy(x, y, target, difficulty);
        }
        // Returns enemy which fires homing missiles
        else if (type.equals("missile")) {
            enemy = EntityFactory.missileEnemy(x, y, target, difficulty);
        }
        return enemy;
    }

    // Returns the type of obstacle according to "type" specified in level file.
    // Uses EntityFactory
    public static IObstacle selectObstacle(double x1, double y1, double x2, double y2, double width, double height, String type) {
        IObstacle obstacle = null;
        // Stationary obstacle
        if (type.equals("wall")) {
            obstacle = EntityFactory.wall(x1, y1, width, height);
        }
        // Obstacle that moves across the level. Moves between points (x1,y1) and (x2,y2)
        else if (type.equals("moving")) {
            obstacle = EntityFactory.movingWall(x1, y1, x2, y2, width, height);
        }
        // Spike obstacle which can kill other entities
        else if (type.equals("spikes")) {
            obstacle = EntityFactory.spikes(x1, x2);
        }
        return obstacle;
    }











}
