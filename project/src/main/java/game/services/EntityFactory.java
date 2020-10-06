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
    private static final int BASEENEMYSTRENGTH = 5;

    private static final long BULLETFREQUENCY = GameLoop.SECOND;
    private static final double BULLETRADIUS = 10;
    private static final double BULLETSPEED = 400;
    private static final int BULLETSTRENGTH = 1;

    // Dictates how much increasing the difficulty increases the frequency of bullets. The higher the more frequent.
    private static final double BULLETFREQUENCYFACTOR = 0.5;

    private static final double MISSILEMAXFORCE = 500;
    private static final double MISSILEMAXSPEED = 400;
    private static final double MISSILEMINSPEED = 200;
    private static final int MISSILESTRENGTH = 3;
    private static final long MISSILEFREQEUNCY = GameLoop.SECOND * 2;
    private static final double MISSILERADIUS = 10;

    // Dictates how much the difficulty changes the max force of the missiles.
    private static final double MISSILEMAXFORCEFACTOR = 300;

    // Dictates how much the difficulty changes the max speed of the missiles.
    private static final double MISSILEMAXSPEEDFACTOR = 25;


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

        // Bullet frequency is impacted by given difficulty.
        long bulletFrequency = (long ) (BULLETFREQUENCY / (difficulty * BULLETFREQUENCYFACTOR));

        // Create Enemy with Ability ShootBullet.
        ShootBullet shootBullet = new ShootBullet(bulletFrequency, BULLETRADIUS, 0, BULLETSPEED, BULLETSTRENGTH);
        Enemy enemy = basicEnemy(x, y, target, BASEENEMYSTRENGTH);
        enemy.setAbilityBehaviour(new SingleAbilityBehavior(shootBullet));

        return enemy;
    }

    public static Enemy rectangleEnemy(double x, double y, IEntity<?> target, int strength) {
        return new Enemy(new Point2D(x,y), 50, 1000, 1000, 1,null, new SeekingBehaviour(), target, strength);
    }

    //Creates an enemy with the ability of homing missiles. The given difficulty changes the max speed and responsiveness of the missiles.
    public static Enemy missileEnemy(double x, double y, IEntity<?> target, int difficulty) {
        if (difficulty < 1) throw new IllegalArgumentException();

        // MovementBehaviour to be used by missiles fired by the Enemy.
        IMovementBehaviour movementBehaviour = new SeekingBehaviour();

        // Max force and max speed of missile are impacted by difficulty.
        double maxForce = MISSILEMAXFORCE + MISSILEMAXFORCEFACTOR * difficulty;
        double maxSpeed = MISSILEMAXSPEED + MISSILEMAXSPEEDFACTOR * difficulty;

        // Create Enemy with Ability ShootMissile.
        ShootMissile shootMissile = new ShootMissile(MISSILEFREQEUNCY, MISSILERADIUS, maxForce, maxSpeed , MISSILEMINSPEED, MISSILESTRENGTH, movementBehaviour);
        Enemy enemy = basicEnemy(x, y, target, BASEENEMYSTRENGTH);
        enemy.setAbilityBehaviour(new SingleAbilityBehavior(shootMissile));

        return enemy;
    }
}
