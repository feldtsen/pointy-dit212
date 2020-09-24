package game.services;

import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.IPlayer;
import game.model.entity.player.Player;
import game.model.shape2d.ICircle;
import javafx.geometry.Point2D;

public class EntityFactory {

    public static Player basicPlayer(double x, double y) {
        return new Player(new Point2D(x, y), 30, 2500, 1000);
    }

    public static Enemy basicEnemy(double x, double y, IEntity target, int strength) {
        return new Enemy(new Point2D(x,y), 50, 1000, 1000, 1,null, new SeekingBehaviour(), target, strength);
    }
}
