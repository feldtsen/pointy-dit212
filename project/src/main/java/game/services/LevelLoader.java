package game.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import game.model.entity.IEntity;
import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.obstacle.Wall;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;
import game.model.level.Level;
import javafx.geometry.Point2D;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

//Class for loading levels using GSON JSON parser.
public class LevelLoader {

    private static JsonObject levelJSON;

    //Constructor takes the level ID in form of a string and parses the level data into a JSON object.
    public static Level load(String levelID) throws FileNotFoundException {
        levelJSON = new JsonParser().parse(new FileReader("src/main/resources/game/levels/" + levelID + ".json")).getAsJsonObject();

        List<IObstacle> obstacles = loadObstacles();
        IPlayer player = loadPlayer();
        List<IEnemy> enemies = loadEnemies(player);
        List<IProjectile<?>> projectiles = loadProjectiles();

        return new Level(enemies, projectiles, obstacles, player, 1200, 675);
    }


    //TODO: Fix target to be dynamic as well as implement other variables
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

    private static IEnemy selectEnemy(double x, double y, IEntity target, int difficulty, String type) {
        IEnemy enemy = null;
        if (type.equals("basic")) {
            enemy = EntityFactory.basicEnemy(x, y,target, difficulty);
        }
        else if (type.equals("bullet")) {
            enemy = EntityFactory.bulletEnemy(x, y, target, difficulty);
        }
        else if (type.equals("missile")) {
            enemy = EntityFactory.missileEnemy(x, y, target, difficulty);
        }
        return enemy;
    }

    //Returns an empty list since no projectiles are loaded at the start of a level.
    private static List<IProjectile<?>> loadProjectiles() {
        return new ArrayList<>();
    }

    private static List<IObstacle> loadObstacles() {
        List<IObstacle> obstacles = new ArrayList<>();
        JsonArray obstacleArr = levelJSON.getAsJsonArray("Enemies");
        for (int i = 0; i < obstacleArr.size(); i++) {
            double x = obstacleArr.get(i).getAsJsonObject().get("x").getAsDouble();
            double y = obstacleArr.get(i).getAsJsonObject().get("y").getAsDouble();
            obstacles.add(new Wall(new Point2D(x,y),20,20));
        }
        return obstacles;
    }

    private static IPlayer loadPlayer() {
        double x = levelJSON.getAsJsonObject("Player").get("x").getAsDouble();
        double y = levelJSON.getAsJsonObject("Player").get("y").getAsDouble();
        return EntityFactory.basicPlayer(x,y);
    }











}
