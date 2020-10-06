package game.services;

import game.controller.gameLoop.GameLoop;
import game.model.ability.ShootBullet;
import game.model.behavior.ability.SingleAbilityBehavior;
import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import javafx.geometry.Point2D;



public class EntityFactory {
    private static final long BASEFREQUENCY = GameLoop.SECOND;
    private static final int BASEENEMYSTRENGTH = 5;
    private static final double BASEBULLETRADIUS = 10;
    private static final double BASEBULLETSPEED = 300 + 100;
    private static final int BASEBULLETSTRENGTH = 1;


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

    //Creates an enemy with the ability of shooting bullets. The higher the given difficulty is, the higher the shooting frequency.
    public static Enemy bulletEnemy(double x, double y, IEntity<?> target, int difficulty) {
        ShootBullet shootBullet = new ShootBullet((long ) (BASEFREQUENCY / (difficulty * 0.5)), BASEBULLETRADIUS, 0, BASEBULLETSPEED, BASEBULLETSTRENGTH);
        Enemy enemy = basicEnemy(x, y, target, BASEENEMYSTRENGTH);
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
