package game.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import game.model.entity.Entity;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.Player;
import game.model.entity.projectile.IProjectile;
import game.model.level.Level;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class LevelLoader implements ILevelLoader {

    private static JsonObject jsonObject;

    private static List<Enemy> enemies;
    private static List<IProjectile<?>> projectiles;
    private static List<IObstacle> obstacles;
    private static Player player;
    private static double width;
    private static double height;


    public static Level load(String levelID) throws FileNotFoundException {
        jsonObject = new JsonParser().parse(new FileReader("src/main/resources/game/levels/" + levelID + ".json")).getAsJsonObject();

        width = loadWidth();
        height = loadHeight();
        obstacles = loadObstacles();
        player = loadPlayer();
        enemies = loadEnemies();
        projectiles = loadProjectiles();

        return new Level(enemies, projectiles, obstacles, player, width, height);
    }


    //TODO: Fix target to be dynamic as well as implement other variables
    private static List<Enemy> loadEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        JsonArray enemyArr = jsonObject.getAsJsonArray("Enemies");
        for (int i = 0; i < enemyArr.size(); i++) {
            double x = enemyArr.get(i).getAsJsonObject().get("x").getAsDouble();
            double y = enemyArr.get(i).getAsJsonObject().get("y").getAsDouble();
            IEntity target = player;
            int strength = enemyArr.get(i).getAsJsonObject().get("strength").getAsInt();
            enemies.add(EntityFactory.basicEnemy(x, y, target, strength));
        }
        return enemies;
    }
    private static List<IProjectile<?>> loadProjectiles() {
        return new ArrayList<>();
    }
    private static List<IObstacle> loadObstacles() {
        //TODO: Implement obstacles
        return new ArrayList<>();
    }
    private static Player loadPlayer() {
        double x = jsonObject.getAsJsonObject("Player").get("x").getAsDouble();
        double y = jsonObject.getAsJsonObject("Player").get("y").getAsDouble();
        return EntityFactory.basicPlayer(x,y);
    }
    private static double loadWidth() {
        return jsonObject.getAsJsonObject("Size").get("width").getAsDouble();
    }
    private static double loadHeight() {
        return jsonObject.getAsJsonObject("Size").get("height").getAsDouble();
    }











}
