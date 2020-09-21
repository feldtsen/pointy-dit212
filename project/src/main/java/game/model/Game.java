package game.model;

import game.model.entity.IEntity;
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

    public List<ILevel> levels;
    public ILevel currentLevel;

    public Game() {
        this.levels = dummyLevels();
        this.currentLevel = levels.get(0);
    }

    public static List<ILevel> dummyLevels() {

        Player player = EntityFactory.basicPlayer(375, 200);
        List<IEntity<?>> enemies = new ArrayList<IEntity<?>>();
        IEntity<ICircle> e1 = EntityFactory.basicEnemy(500, 650, player);
        enemies.add(e1);

        ILevel level = new Level(enemies, null, null, player, 1200, 800);
        List<ILevel> levels = new ArrayList<ILevel>();
        levels.add(level);
        return levels;
    }

    public static void containToBounds(MovableEntity<ICircle> entity) {
        //TODO: dummy level, use currentLevel
        ILevel level = new Level(null, null, null, null, 1200, 800);

        double width = level.getWidth();
        double height = level.getHeight();

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


}



