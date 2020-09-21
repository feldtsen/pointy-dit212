package game.services;

import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.player.IPlayer;
import game.model.player.Player;
import game.model.shape2d.ICircle;
import javafx.geometry.Point2D;

public class EntityFactory {

    public static Player basicPlayer() {
        return new Player(new Point2D(575, 375), 30, 2500, 1000);
    }
    public static IEntity<ICircle> basicEnemy(Player player) {
        return new Enemy(new Point2D(100,100), 50, 1000, 1000, 1,null, new SeekingBehaviour(), player);
    }
}
