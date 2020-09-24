package game.model;

import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.movable.MovableEntity;
import game.model.entity.player.IPlayer;
import game.model.level.ILevel;
import game.model.level.Level;
import game.model.entity.player.Player;
import game.model.shape2d.ICircle;
import game.services.EntityFactory;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {

    private final List<ILevel> levels;
    private ILevel currentLevel;
    private int score;


    public Game(List<ILevel> levels) {
        this.levels = levels;
        this.currentLevel = levels.get(0);
        this.score = 0;
    }

    public Game() {
        this.levels = dummyLevels();
        this.currentLevel = levels.get(0);
        this.score = 0;
    }

    public static List<ILevel> dummyLevels() {

        Player player = EntityFactory.basicPlayer(375, 200);
        player.setFriction(3);

        List<Enemy> enemies = new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500, 650, player, 0);
        e1.setFriction(3);
        enemies.add(e1);

        ILevel level = new Level(enemies, null, null, player, 1200, 800);
        List<ILevel> levels = new ArrayList<ILevel>();
        levels.add(level);
        return levels;
    }

    @Override
    public void update(double delta, double timestep) {
        Player player = currentLevel.getPlayer();
        player.update(delta, timestep);
        containToBounds(player);

        for (Enemy enemy : currentLevel.getEnemies()) {
            enemy = currentLevel.getEnemies().get(0);
            enemy.update(delta, timestep);
            containToBounds(enemy);
        }
        for (int i = 0; i < currentLevel.getEnemies().size(); i++) {
            Enemy e1 = currentLevel.getEnemies().get(i);
            for (int j = i+1; j < currentLevel.getEnemies().size(); j++ ){
                Enemy e2 = currentLevel.getEnemies().get(j);
                if (e1.checkCollision(e2)) {
                    //TODO invent the wheel
                    handleCollision(e1,e2);
                }
            }

            if (player.checkCollision(e1)){
                if (player.getStrength() < e1.getStrength()){
                    player.setHitPoints(0);
                }
            }
        }

    }

    public void containToBounds(MovableEntity<ICircle> entity) {
        double width = currentLevel.getWidth();
        double height = currentLevel.getHeight();

        Point2D v = entity.getVelocity();
        Point2D p = entity.getPosition();
        double r = entity.getShape().getRadius(); //TODO: hard coded for circles right now

        if(p.getX() - r < 0) {
            p = new Point2D(r, p.getY());
            v = new Point2D(0, v.getY());
        }
        else if(p.getX() + r >= width) {
            p = new Point2D(width - r, p.getY());
            v = new Point2D(0, v.getY());
        }

        if(p.getY() - r < 0) {
            p = new Point2D(p.getX(), r);
            v = new Point2D(v.getX(), 0);
        }
        else if(p.getY() + r >= height) {
            p = new Point2D(p.getX(), height - r);
            v = new Point2D(v.getX(), 0);
        }

        entity.setPosition(p);
        entity.setVelocity(v);
    }

    @Override
    public boolean setLevel(ILevel level) {
        if(!levels.contains(level)) return false;
        this.currentLevel = level;
        return true;
    }

    @Override
    public ILevel getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public List<ILevel> getLevels() {
        return levels;
    }

    @Override
    public int getScore() {
        return score;
    }

    private void handleCollision(IEntity<?> e1, IEntity<?> e2){
        //TODO inventing the wheel


    }
}



