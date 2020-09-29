package game.services;

import game.model.ability.ShootBullet;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.AbilityBehaviour;
import game.model.behavior.ability.SingleAbilityBehavior;
import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import javafx.geometry.Point2D;

import java.util.List;


public class EntityFactory {

    public static Player basicPlayer(double x, double y) {
        return new Player(new Point2D(x, y), 30, 2500, 1000,0);
    }

    //Creates a player with a higher strength than basicPlayer.
    public static Player strongPlayer(double x, double y) {
        return new Player(new Point2D(x, y), 30, 2500, 1000,10);
    }

    //Creates and enemy without abilities which only follows a target.
    public static Enemy basicEnemy(double x, double y, IEntity<?> target, int strength) {
        return new Enemy(new Point2D(x,y), 50, 1000, 1000, 1,null, new SeekingBehaviour(), target, strength);
    }

    public static Enemy bulletEnemy(double x, double y, IEntity<?> target, int strength, long frequency, double bulletRadius, double bulletSpeed, int bulletStrength) {
        ShootBullet shootBullet = new ShootBullet(frequency, bulletRadius, bulletSpeed, bulletSpeed, bulletStrength);
        Enemy enemy = basicEnemy(x, y, target, strength);
        enemy.setAbilityBehaviour(new SingleAbilityBehavior(shootBullet));
        return enemy;
    }

    public static Enemy rectangleEnemy(double x, double y, IEntity<?> target, int strength) {
        return new Enemy(new Point2D(x,y), 50, 1000, 1000, 1,null, new SeekingBehaviour(), target, strength);
    }

    //Creates an enemy with the ability of homing missiles.
    public static Enemy missileEnemy(double x, double y, IEntity<?> target, int strength) {
        //TODO
        return null;
    }
}
