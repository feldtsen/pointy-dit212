package game.model;

import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.movable.MovableEntity;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.projectile.IProjectile;
import game.controller.gameLoop.GameLoop;
import game.model.level.ILevel;
import game.model.level.Level;
import game.model.entity.player.Player;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.model.shape2d.IShape2D;
import game.services.EntityFactory;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {

    private final List<ILevel> levels;
    private ILevel currentLevel;

    private final List<IAbilityAction> activeAbilityActions;
    private final List<Long> activationTimes;

    private int score;


    public Game(List<ILevel> levels) {
        this.levels = levels;
        this.currentLevel = levels.get(0);
        this.score = 0;

        this.activeAbilityActions = new ArrayList<>();
        this.activationTimes = new ArrayList<>();
    }

    public Game() {
        this(dummyLevels());
    }

    public static List<ILevel> dummyLevels() {

        Player player = EntityFactory.strongPlayer(375, 200);
        player.setFriction(3);

        List<Enemy> enemies = new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500, 650, player, 5);
        e1.setFriction(3);
        enemies.add(e1);

        Enemy e2 = EntityFactory.basicEnemy(900, 850, player, 5);
        e2.setFriction(3);
        enemies.add(e2);

        List<IProjectile<?>> projectiles = new ArrayList<>();

        List<IObstacle> obstacles = new ArrayList<>();

        ILevel level = new Level(enemies, projectiles, obstacles, player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);
        return levels;
    }

    private void activateAbility(IAbilityAction action) {
        Long now = System.nanoTime();
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

        // Remove finished ability actions
        // Iterate backwards to avoid problems with removing entries from list
        for(int i = activeAbilityActions.size() - 1;  i >= 0; i--) {
            IAbilityAction abilityAction = activeAbilityActions.get(i);
            long activationTime = activationTimes.get(i);
            if(now - activationTime > abilityAction.getDuration() * GameLoop.SECOND) {
                deactivateAbility(i);
            }
        }

        // Update player
        Player player = currentLevel.getPlayer();
        player.update(delta, timeStep);
        containToBounds(player);

        // Check for collisions between player and projectiles. Adjust players hit points if collision occurs.
        for (IProjectile<?> projectile : currentLevel.getProjectiles()) {
            if (player.checkCollision(projectile)) {
                int previousHitPoints = player.getHitPoints();
                int damage = projectile.getStrength();
                int newHitPoints = previousHitPoints - damage;

                player.setHitPoints(newHitPoints);
            }
        }

        // Update all enemies
        for (Enemy enemy : currentLevel.getEnemies()) {
            enemy.update(delta, timeStep);
            containToBounds(enemy);

            // Activate enemy abilities
            IAbilityAction abilityAction = enemy.applyAbility();
            if(abilityAction != null) {
                activeAbilityActions.add(abilityAction);
                activationTimes.add(now);
            }
        }

        // Apply active abilities
        for(int i = 0; i < activeAbilityActions.size(); i++) {
            IAbilityAction abilityAction = activeAbilityActions.get(i);
            long activationTime = activationTimes.get(i);
            abilityAction.apply(currentLevel, (double)(now - activationTime) / GameLoop.SECOND);
        }

        // Check collision between players and enemies, and enemies and other enemies
        for (int i = 0; i < currentLevel.getEnemies().size(); i++) {
            Enemy e1 = currentLevel.getEnemies().get(i);
            for (int j = i+1; j < currentLevel.getEnemies().size(); j++ ){
                Enemy e2 = currentLevel.getEnemies().get(j);
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
                }
                else {
                    e1.setHitPoints(0);
                    currentLevel.removeEnemy(e1);
                }
            }
        }


    }

    // Makes sure an entity does not leave the map bounds
    public void containToBounds(MovableEntity<ICircle> entity) {
        double width = currentLevel.getWidth();
        double height = currentLevel.getHeight();

        Point2D v = entity.getVelocity();
        Point2D p = entity.getPosition();
        double r = entity.getShape().getRadius();

        if(p.getX() - r < 0) {
            p = new Point2D(r, p.getY());
            v = new Point2D(0, v.getY());
        }
        else if(p.getX() + r >= width) {
            p = new Point2D(width - r, p.getY());
            v = new Point2D(0, v.getY());
        }

        if(p.getY() - r < 0) {
            p = new Point2D(p.getX(), r);
            v = new Point2D(v.getX(), 0);
        }
        else if(p.getY() + r >= height) {
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
    public List<IAbilityAction> activeAbilityActions() {
        return activeAbilityActions;
    }

    @Override
    public int getScore() {
        return score;
    }

    private void handleCollision(IEntity<?> e1, IEntity<?> e2){
        //TODO inventing the wheel


    }
}



