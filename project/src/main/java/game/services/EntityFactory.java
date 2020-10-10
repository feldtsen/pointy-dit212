package game.services;

import game.controller.gameLoop.GameLoop;
import game.model.ability.ShootBullet;
import game.model.ability.ShootMissile;
import game.model.behavior.ability.SingleAbilityBehavior;
import game.model.behavior.movement.FleeingBehaviour;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import javafx.geometry.Point2D;

public class EntityFactory {
    private static final double FLEEING_ENEMY_CLOSEST_DISTANCE = 300; // The closest distance an enemy with FleeingBehaviour will get to the target before moving away.
    private static final double FLEEING_ENEMY_FURTHEST_DISTANCE = 500; // The furthest distance an enemy with Fleeing can have before moving towards target.

    private static final int BASE_ENEMY_STRENGTH = 5;

    private static final long BULLET_FREQUENCY = GameLoop.SECOND;
    private static final double BULLET_RADIUS = 5;
    private static final double BULLET_SPEED = 700;
    private static final int BULLET_STRENGTH = 1;

    // Dictates how much increasing the difficulty increases the frequency of bullets. The higher the more frequent.
    private static final double BULLET_FREQUENCY_FACTOR = 0.5;

    private static final double MISSILE_MAX_FORCE = 200;
    private static final double MISSILE_MAX_SPEED = 400;
    private static final double MISSILE_MIN_SPEED = 400;
    private static final int MISSILE_STRENGTH = 3;
    private static final long MISSILE_FREQUENCY = GameLoop.SECOND * 2;
    private static final double MISSILE_WIDTH = 10;
    private static final double MISSILE_HEIGHT = 15;

    // Dictates how much the difficulty changes the max force of the missiles.
    private static final double MISSILE_MAX_FORCE_FACTOR = 300;

    // Dictates how much the difficulty changes the max speed of the missiles.
    private static final double MISSILE_MAX_SPEED_FACTOR = 25;

    private static final double FRICTION = 3;

    private EntityFactory() {}

    public static Player basicPlayer(double x, double y) {
        Player player = new Player(new Point2D(x, y), 20, 2500, 1000,0);
        player.setFriction(FRICTION);
        return player;
    }

    //Creates a player with a higher strength than basicPlayer.
    public static Player strongPlayer(double x, double y) {
        return new Player(new Point2D(x, y), 30, 2500, 1000,10);
    }

    //Creates and enemy without abilities which only follows a target.
    public static Enemy basicEnemy(double x, double y, IEntity<?> target, int strength) {
        Enemy enemy = new Enemy(new Point2D(x,y), 25, 1000, 1000, 1,null, new SeekingBehaviour(), target, strength);
        enemy.setFriction(FRICTION);
        return enemy;
    }

    //Creates an enemy with the ability of shooting bullets. The higher the given difficulty is, the higher the shooting frequency.
    public static Enemy bulletEnemy(double x, double y, IEntity<?> target, int difficulty) {
        if (difficulty < 1) throw new IllegalArgumentException();

        // Bullet frequency is impacted by given difficulty.
        long bulletFrequency = (long ) (BULLET_FREQUENCY / (difficulty * BULLET_FREQUENCY_FACTOR));

        IMovementBehaviour movementBehaviour = new FleeingBehaviour(FLEEING_ENEMY_CLOSEST_DISTANCE, FLEEING_ENEMY_FURTHEST_DISTANCE);

        // Create Enemy with Ability ShootBullet.
        ShootBullet shootBullet = new ShootBullet(bulletFrequency, BULLET_RADIUS, 0, BULLET_SPEED, BULLET_STRENGTH);
        Enemy enemy = basicEnemy(x, y, target, BASE_ENEMY_STRENGTH);
        enemy.setMovementBehaviour(movementBehaviour);
        enemy.setAbilityBehaviour(new SingleAbilityBehavior(shootBullet));
        enemy.setFriction(FRICTION);

        return enemy;
    }

    public static Enemy rectangleEnemy(double x, double y, IEntity<?> target, int strength) {
        return new Enemy(new Point2D(x,y), 50, 1000, 1000, 1,null, new SeekingBehaviour(), target, strength);
    }

    //Creates an enemy with the ability of homing missiles. The given difficulty changes the max speed and responsiveness of the missiles.
    public static Enemy missileEnemy(double x, double y, IEntity<?> target, int difficulty) {
        if (difficulty < 1) throw new IllegalArgumentException();

        // MovementBehaviour to be used by missiles fired by the Enemy.
        IMovementBehaviour missileMovementBehaviour = new SeekingBehaviour();

        IMovementBehaviour movementBehaviour = new FleeingBehaviour(FLEEING_ENEMY_CLOSEST_DISTANCE, FLEEING_ENEMY_FURTHEST_DISTANCE);

        // Max force and max speed of missile are impacted by difficulty.
        double maxForce = MISSILE_MAX_FORCE + MISSILE_MAX_FORCE_FACTOR * difficulty;
        double maxSpeed = MISSILE_MAX_SPEED + MISSILE_MAX_SPEED_FACTOR * difficulty;

        // Create Enemy with Ability ShootMissile.
        ShootMissile shootMissile = new ShootMissile(MISSILE_FREQUENCY, MISSILE_WIDTH, MISSILE_HEIGHT, maxForce, maxSpeed , MISSILE_MIN_SPEED, MISSILE_STRENGTH, missileMovementBehaviour);
        Enemy enemy = basicEnemy(x, y, target, BASE_ENEMY_STRENGTH);
        enemy.setMovementBehaviour(movementBehaviour);
        enemy.setAbilityBehaviour(new SingleAbilityBehavior(shootMissile));
        enemy.setFriction(FRICTION);

        return enemy;
    }
}
