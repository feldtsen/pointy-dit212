package game.services;

import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import game.model.shape2d.ICircle;
import javafx.geometry.Point2D;

public class EntityFactory {

    public static Player basicPlayer(double x, double y) {
        return new Player(new Point2D(x, y), 30, 2500, 1000);
    }
    public static IEntity<ICircle> basicEnemy(double x, double y, IEntity target) {
        return new Enemy(new Point2D(x,y), 50, 1000, 1000, 1,null, new SeekingBehaviour(), target);
    }
}
