package game.services;

import game.controller.gameLoop.GameLoop;
import game.model.ability.*;
import game.model.behavior.ability.SingleAbilityBehavior;
import game.model.behavior.movement.FleeingBehaviour;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.obstacle.MovingWall;
import game.model.entity.obstacle.Wall;
import game.model.entity.player.Player;
import javafx.geometry.Point2D;

// Factory for creating entities.
public class EntityFactory {
    private static final double FLEEING_ENEMY_CLOSEST_DISTANCE = 300; // The closest distance an enemy with FleeingBehaviour will get to the target before moving away.
    private static final double FLEEING_ENEMY_FURTHEST_DISTANCE = 500; // The furthest distance an enemy with Fleeing can have before moving towards target.

    private static final int    BASE_ENEMY_STRENGTH = 5;

    private static final long   BULLET_FREQUENCY = GameLoop.SECOND;
    private static final double BULLET_RADIUS = 5;
    private static final double BULLET_SPEED = 700;
    private static final int    BULLET_STRENGTH = 1;

    // Dictates how much increasing the difficulty increases the frequency of bullets. The higher the more frequent.
    private static final double BULLET_FREQUENCY_FACTOR = 0.5;

    private static final double MISSILE_MAX_FORCE = 200;
    private static final double MISSILE_MAX_SPEED = 400;
    private static final double MISSILE_MIN_SPEED = 400;
    private static final int    MISSILE_STRENGTH = 3;
    private static final long   MISSILE_FREQUENCY = GameLoop.SECOND * 2;
    private static final double MISSILE_WIDTH = 10;
    private static final double MISSILE_HEIGHT = 15;

    // Dictates how much the difficulty changes the max force of the missiles.
    private static final double MISSILE_MAX_FORCE_FACTOR = 300;

    // Dictates how much the difficulty changes the max speed of the missiles.
    private static final double MISSILE_MAX_SPEED_FACTOR = 25;

    private static final double MOVING_WALL_MAX_FORCE = 200;
    private static final double MOVING_WALL_MAX_SPEED = 400;

    private static final double FRICTION = 3;

    private EntityFactory() {}

    // Creates a basic player with three abilities.
    public static Player basicPlayer(double x, double y) {

        // Creates player at given position.
        Player player = new Player(new Point2D(x, y), 20, 2500, 1000,0);
        player.setFriction(FRICTION);

        // Adds the abilities that should be available to the player.
        player.addAbility(new Dash((GameLoop.SECOND * 2), 3000, 5)); // First ability activated on shift
        player.addAbility(new Shockwave(GameLoop.SECOND * 2, 300, 100000, 0.1)); // Second ability activated on E
        player.addAbility(new Reflect(GameLoop.SECOND / 2, Math.PI/2, 200, 0.5, 0.1, 1000)); // Third ability activated on click
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

        // The MovementBehaviour to be used by the enemy.
        IMovementBehaviour movementBehaviour = new FleeingBehaviour(FLEEING_ENEMY_CLOSEST_DISTANCE, FLEEING_ENEMY_FURTHEST_DISTANCE);

        // The Ability that will be used by the enemy.
        ShootBullet shootBullet = new ShootBullet(bulletFrequency, BULLET_RADIUS, 0, BULLET_SPEED, BULLET_STRENGTH);

        // Create enemy. Add MovementBehaviour and Ability.
        Enemy enemy = basicEnemy(x, y, target, BASE_ENEMY_STRENGTH);
        enemy.setMovementBehaviour(movementBehaviour);
        enemy.setAbilityBehaviour(new SingleAbilityBehavior(shootBullet));
        enemy.setFriction(FRICTION);

        return enemy;
    }

    //Creates an enemy with the ability of homing missiles. The given difficulty changes the max speed and responsiveness of the missiles.
    public static Enemy missileEnemy(double x, double y, IEntity<?> target, int difficulty) {
        if (difficulty < 1) throw new IllegalArgumentException();

        // MovementBehaviour to be used by missiles fired by the Enemy.
        IMovementBehaviour missileMovementBehaviour = new SeekingBehaviour();

        // MovementBehaviour to be used by the enemy.
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

    public static IObstacle wall(double x, double y, double width, double height) {
        return new Wall(new Point2D(x,y), width, height);
    }

    public static IObstacle movingWall(double x1, double y1, double x2, double y2, double width, double height) {
        return new MovingWall(new Point2D(x1,y1), new Point2D(x2, y2), MOVING_WALL_MAX_SPEED, MOVING_WALL_MAX_FORCE, width, height);
    }

    public static IObstacle spikes(double x, double y) {
        //TODO
        return null;
    }
}
