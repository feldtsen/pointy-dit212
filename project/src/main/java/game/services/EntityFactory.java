package game.services;

import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import javafx.geometry.Point2D;

public class EntityFactory {

    public static Player basicPlayer(double x, double y) {
        return new Player(new Point2D(x, y), 30, 2500, 1000,0);
    }

    public static Player strongPlayer(double x, double y) {
        return new Player(new Point2D(x, y), 30, 2500, 1000,10);
    }

    public static Enemy basicEnemy(double x, double y, IEntity target, double strength) {
        return new Enemy(new Point2D(x,y), 50, 1000, 1000, 1,null, new SeekingBehaviour(), target, strength);
    }

    public static Enemy rectangleEnemy(double x, double y, IEntity target, int strength) {
        return new Enemy(new Point2D(x,y), 50, 1000, 1000, 1,null, new SeekingBehaviour(), target, strength);
    }
}
