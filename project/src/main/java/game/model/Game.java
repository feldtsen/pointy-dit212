package game.model;

import game.model.ability.Shockwave;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.movable.MovableEntity;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;
import game.controller.gameLoop.GameLoop;
import game.model.level.ILevel;
import game.model.level.Level;
import game.model.entity.player.Player;
import game.model.shape2d.ICircle;
import game.services.EntityFactory;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {
    private int score;
    private boolean gameOver;

    private List<ILevel> levels;
    private ILevel currentLevel;

    // This list is continuously updated with the active ability actions.
    // An action is removed when it's duration has run out.
    private final List<IAbilityAction> activeAbilityActions;
    // This is a helper list containing the nano time of the activation of a certain ability.
    // Index n in this list corresponds to index n in activeAbilityActions.
    private final List<Long> activationTimes;

    // TODO: take level loader instead!
    public Game(List<ILevel> levels) {
        this.levels = levels;
        this.currentLevel = levels.get(0);
        this.score = 0;
        this.gameOver = false;

        this.activeAbilityActions = new ArrayList<>();
        this.activationTimes = new ArrayList<>();
    }

    public Game() {
        this(dummyLevels());
    }

    public static List<ILevel> dummyLevels() {


        IPlayer player = EntityFactory.basicPlayer(375, 200);
        player.setFriction(3);
        player.addAbility(new Shockwave(GameLoop.SECOND * 2, 300, 100000, 0.1));

        List<IEnemy> enemies = new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500, 650, player, 5);
        e1.setFriction(3);
        enemies.add(e1);

        //Enemy e2 = EntityFactory.basicEnemy(900, 850, player, 5);
        Enemy e2 = EntityFactory.bulletEnemy(900, 850, player, 5, GameLoop.SECOND / 2, 10, 800, 1);
        e2.setFriction(3);
        enemies.add(e2);

        List<IProjectile<?>> projectiles = new ArrayList<>();

        List<IObstacle> obstacles = new ArrayList<>();

        ILevel level = new Level(enemies, projectiles, obstacles, player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);
        return levels;
    }

    //TODO: should probably not be public, for testing purposes only
    public void activateAbility(IAbilityAction action, long now) {
        if(action == null) return;
        activeAbilityActions.add(action);
        activationTimes.add(now);
    }

    private void deactivateAbility(int index) {
        activeAbilityActions.remove(index);
        activationTimes.remove(index);
    }

    @Override
    public void update(double delta, double timeStep) {
        // Nanos passed since last update
        long now = System.nanoTime();

        // Check for player death
        if(!currentLevel.getPlayer().isAlive()) {
            gameOver();
        }

        // Update player
        IPlayer player = currentLevel.getPlayer();
        player.update(delta, timeStep);
        containToBounds(player);

        // Check for collisions between player and projectiles. Adjust players hit points if collision occurs.
        for (int i = currentLevel.getProjectiles().size() - 1; i >= 0; i--) {
            IProjectile<?> projectile = currentLevel.getProjectiles().get(i);
            projectile.update(delta, timeStep);

            if(isOutOfBounds(projectile)) {
                currentLevel.getProjectiles().remove(i);
                continue;
            }

            if (player.checkCollision(projectile)) {
                player.setHitPoints(player.getHitPoints() - projectile.getStrength());
            }
        }

        // Update all enemies
        for (IEnemy enemy : currentLevel.getEnemies()) {
            enemy.update(delta, timeStep);
            containToBounds(enemy);

            // Activate enemy abilities
            IAbilityAction abilityAction = enemy.applyAbility();
            if(abilityAction != null) {
                activateAbility(abilityAction, now);
            }
        }

        // Apply active abilities
        for(int i = 0; i < activeAbilityActions.size(); i++) {
            IAbilityAction abilityAction = activeAbilityActions.get(i);
            long activationTime = activationTimes.get(i);
            // Feed the ability the time since activation (in seconds). Some ability will depend on this value.
            abilityAction.apply(currentLevel, (double)(now - activationTime) / GameLoop.SECOND);
        }

        // Check collision between players and enemies, and enemies and other enemies
        for (int i = 0; i < currentLevel.getEnemies().size(); i++) {
            IEnemy e1 = currentLevel.getEnemies().get(i);
            // Loop from i + 1 to ensure collision is not checked twice for each entity pair, and to avoid checking
            // self collision checking.
            for (int j = i + 1; j < currentLevel.getEnemies().size(); j++ ){
                IEnemy e2 = currentLevel.getEnemies().get(j);
                if (e1.checkCollision(e2)) {
                    //TODO invent the wheel
                    handleCollision(e1,e2);
                }
            }

            // Check player-enemy collision
            if (player.checkCollision(e1)){
                // If enemy is stronger than player, player dies :(
                if (player.getStrength() < e1.getStrength()){
                    player.setHitPoints(0);
                } else {
                    e1.setHitPoints(0);
                    currentLevel.removeEnemy(e1);
                }
            }
        }

        // Remove finished ability actions
        // Iterate backwards to avoid problems with removing entries from list
        for(int i = activeAbilityActions.size() - 1;  i >= 0; i--) {
            IAbilityAction abilityAction = activeAbilityActions.get(i);
            long activationTime = activationTimes.get(i);
            // Check if the time since activation time exceeds the duration of the abilityAction.
            if(now - activationTime > abilityAction.getDuration() * GameLoop.SECOND) {
                // If true, deactivate ability.
                deactivateAbility(i);
            }
        }
    }

    private void gameOver() {
        // TODO: handle player death
        this.gameOver = true;
    }

    public boolean isOutOfBounds(IEntity<?> entity) {
        return entity.getPosition().getX() < 0 || entity.getPosition().getX() >= currentLevel.getWidth() ||
               entity.getPosition().getY() < 0 || entity.getPosition().getY() >= currentLevel.getHeight();
    }

    // Makes sure an entity does not leave the map bounds
    public void containToBounds(IMovable<ICircle> entity) {
        double width = currentLevel.getWidth();
        double height = currentLevel.getHeight();

        // Velocity is later set to 0 in the direction of collision.
        Point2D v = entity.getVelocity();

        Point2D p = entity.getPosition();
        double r = entity.getShape().getRadius();

        // Collision with left wall
        if(p.getX() - r < 0) {
            p = new Point2D(r, p.getY());
            v = new Point2D(0, v.getY());
        // Collision with right wall
        } else if(p.getX() + r >= width) {
            p = new Point2D(width - r, p.getY());
            v = new Point2D(0, v.getY());
        }
        // Collision with top wall
        if(p.getY() - r < 0) {
            p = new Point2D(p.getX(), r);
            v = new Point2D(v.getX(), 0);
        // Collision with bottom wall
        } else if(p.getY() + r >= height) {
            p = new Point2D(p.getX(), height - r);
            v = new Point2D(v.getX(), 0);
        }

        entity.setPosition(p);
        entity.setVelocity(v);
    }

    @Override
    public boolean setLevel(ILevel level) {
        if(!levels.contains(level)) return false;
        this.currentLevel = level;
        return true;
    }

    @Override
    public ILevel getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public List<ILevel> getLevels() {
        return levels;
    }

    @Override
    public List<IAbilityAction> getActiveAbilityActions() {
        return activeAbilityActions;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    private void handleCollision(IEntity<?> e1, IEntity<?> e2){
        //TODO inventing the wheel


    }
}



