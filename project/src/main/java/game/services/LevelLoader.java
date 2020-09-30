package game.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import game.model.entity.IEntity;
import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;
import game.model.level.Level;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

//Class for loading levels using GSON JSON parser.
public class LevelLoader {

    private static JsonObject levelJSON;

    private static List<IEnemy> enemies;
    private static List<IProjectile<?>> projectiles;
    private static List<IObstacle> obstacles;
    private static IPlayer player;
    private static double width;
    private static double height;

    //Constructor takes the level ID in form of a string and parses the level data into a JSON object.
    public static Level load(String levelID) throws FileNotFoundException {
        levelJSON = new JsonParser().parse(new FileReader("src/main/resources/game/levels/" + levelID + ".json")).getAsJsonObject();

        width = loadWidth();
        height = loadHeight();
        obstacles = loadObstacles();
        player = loadPlayer();
        enemies = loadEnemies();
        projectiles = loadProjectiles();

        return new Level(enemies, projectiles, obstacles, player, width, height);
    }


    //TODO: Fix target to be dynamic as well as implement other variables
    private static List<IEnemy> loadEnemies() {
        List<IEnemy> enemies = new ArrayList<>();
        JsonArray enemyArr = levelJSON.getAsJsonArray("Enemies");
        for (int i = 0; i < enemyArr.size(); i++) {
            double x = enemyArr.get(i).getAsJsonObject().get("x").getAsDouble();
            double y = enemyArr.get(i).getAsJsonObject().get("y").getAsDouble();
            int strength = enemyArr.get(i).getAsJsonObject().get("strength").getAsInt();
            enemies.add(EntityFactory.basicEnemy(x, y, player, strength));
        }
        return enemies;
    }

    //Returns an empty list since no projectiles are loaded at the start of a level.
    private static List<IProjectile<?>> loadProjectiles() {
        return new ArrayList<>();
    }

    private static List<IObstacle> loadObstacles() {
        //TODO: Implement obstacles
        return new ArrayList<>();
    }

    private static IPlayer loadPlayer() {
        double x = levelJSON.getAsJsonObject("Player").get("x").getAsDouble();
        double y = levelJSON.getAsJsonObject("Player").get("y").getAsDouble();
        return EntityFactory.basicPlayer(x,y);
    }

    private static double loadWidth() {
        return levelJSON.getAsJsonObject("Size").get("width").getAsDouble();
    }

    private static double loadHeight() {
        return levelJSON.getAsJsonObject("Size").get("height").getAsDouble();
    }











}
