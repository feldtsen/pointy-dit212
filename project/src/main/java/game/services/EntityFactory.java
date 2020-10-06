package game.services;

import game.controller.gameLoop.GameLoop;
import game.model.ability.ShootBullet;
import game.model.ability.ShootMissile;
import game.model.behavior.ability.SingleAbilityBehavior;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import javafx.geometry.Point2D;



public class EntityFactory {
    private static final long BASEBULLETFREQUENCY = GameLoop.SECOND;
    private static final int BASEENEMYSTRENGTH = 5;
    private static final double BASEBULLETRADIUS = 10;
    private static final double BASEBULLETSPEED = 400;
    private static final int BASEBULLETSTRENGTH = 1;

    private static final double BASEMISSILEMAXFORCE = 500;
    private static final double BASEMISSILEMAXSPEED = 400;
    private static final double BASEMISSILEMINSPEED = 200;
    private static final int BASEMISSILESTRENGTH = 3;
    private static final long BASEMISSILEFREQEUNCY = GameLoop.SECOND * 2;
    private static final double BASEMISSILERADIUS = 10;


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
        if (difficulty < 1) throw new IllegalArgumentException();

        ShootBullet shootBullet = new ShootBullet((long ) (BASEBULLETFREQUENCY / (difficulty * 0.5)), BASEBULLETRADIUS, 0, BASEBULLETSPEED, BASEBULLETSTRENGTH);
        Enemy enemy = basicEnemy(x, y, target, BASEENEMYSTRENGTH);
        enemy.setAbilityBehaviour(new SingleAbilityBehavior(shootBullet));
        return enemy;
    }

    public static Enemy rectangleEnemy(double x, double y, IEntity<?> target, int strength) {
        return new Enemy(new Point2D(x,y), 50, 1000, 1000, 1,null, new SeekingBehaviour(), target, strength);
    }

    //Creates an enemy with the ability of homing missiles. The given difficulty changes the max speed and responsiveness of the missiles.
    public static Enemy missileEnemy(double x, double y, IEntity<?> target, int difficulty) {
        IMovementBehaviour movementBehaviour = new SeekingBehaviour();
        ShootMissile shootMissile = new ShootMissile(BASEMISSILEFREQEUNCY, BASEMISSILERADIUS, BASEMISSILEMAXFORCE + 300 * difficulty, BASEMISSILEMAXSPEED + 25 * difficulty , BASEMISSILEMINSPEED, BASEMISSILESTRENGTH, movementBehaviour);
        Enemy enemy = basicEnemy(x, y, target, BASEENEMYSTRENGTH);
        enemy.setAbilityBehaviour(new SingleAbilityBehavior(shootMissile));
        return enemy;
    }
}
