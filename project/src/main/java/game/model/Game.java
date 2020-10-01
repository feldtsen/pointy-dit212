package game.model;

import game.model.ability.Shockwave;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.movable.IMovable;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;
import game.controller.gameLoop.GameLoop;
import game.model.level.ILevel;
import game.model.level.Level;
import game.model.shape2d.ICircle;
import game.services.EntityFactory;
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {
    private int score; // Player score
    private boolean gameOver;

    private final List<ILevel> levels;
    private ILevel currentLevel;

    // This value represents the position the player is "looking towards".
    private Point2D playerFacingPosition;

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

        this.playerFacingPosition = new Point2D(currentLevel.getWidth()/2, currentLevel.getHeight()/2); // Default direciton
    }

    public Game() {
        this(dummyLevels());
    }

    // TODO: dummy levels is a temporary method used for testing. Replace with levels loaded from level loader
    public static List<ILevel> dummyLevels() {
        // Create player with shockwave ability
        IPlayer player = EntityFactory.basicPlayer(375, 200);
        player.setFriction(3);
        player.addAbility(new Shockwave(GameLoop.SECOND * 2, 300, 100000, 0.1));

        // Create basic enemy
        List<IEnemy> enemies = new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500, 650, player, 5);
        e1.setFriction(3);
        enemies.add(e1);

        // Create bullet enemy
        Enemy e2 = EntityFactory.bulletEnemy(900, 850, player, 5, GameLoop.SECOND / 2, 10, 800, 1);
        e2.setFriction(3);
        enemies.add(e2);

        // Create empty lists for projectiles and obstacles
        List<IProjectile<?>> projectiles = new ArrayList<>();
        List<IObstacle> obstacles = new ArrayList<>();

        // Build level(s)
        ILevel level = new Level(enemies, projectiles, obstacles, player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);
        return levels;
    }

    // Activate ability adds an ability to the active ability actions list, together with the
    // corresponding activation time.
    private void activateAbility(IAbilityAction action, long now) {
        if(action == null) return;
        activeAbilityActions.add(action);
        activationTimes.add(now);
    }

    // Deactivates (removes) an ability action at a particular index of the list.
    private void deactivateAbility(int index) {
        activeAbilityActions.remove(index);
        activationTimes.remove(index);
    }

    // Update is called every game loop iteration (frame). Update is the method responsible for
    // handling all model updates.
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
        containToBounds(player); // Ensures the player cannot leave the map

        // Sett the facing direction of the player
        Point2D direction = playerFacingPosition.subtract(player.getPosition());
        double angle = Utils.heading(direction);
        player.getShape().setRotation(angle);

        // Check for collisions between player and projectiles. Adjust players hit points if collision occurs.
        // Iterates backwards to enable removing projectiles from the list at the same time as looping.
        for (int i = currentLevel.getProjectiles().size() - 1; i >= 0; i--) {
            IProjectile<?> projectile = currentLevel.getProjectiles().get(i);
            // Update projectile
            projectile.update(delta, timeStep);

            // Remove projectile from list if destroyed or out of bounds
            if(projectile.isDestroyed() || isOutOfBounds(projectile)) {
                currentLevel.getProjectiles().remove(i);
                continue;
            }

            // Check collision with player, and reduce player hit points if collision.
            if (player.checkCollision(projectile)) {
                player.setHitPoints(player.getHitPoints() - projectile.getStrength());
            }
        }

        // Update all enemies
        for (IEnemy enemy : currentLevel.getEnemies()) {
            enemy.update(delta, timeStep);
            containToBounds(enemy); // Ensure enemies cannot leave map.

            // Activate enemy abilities
            activateAbility(enemy.applyAbility(), now);
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
                    handleCollision(e1,e2);
                }
            }

            // Check player-enemy collision
            if (player.checkCollision(e1)){
                // If enemy is stronger than player, player dies
                if (player.getStrength() < e1.getStrength()){
                    player.setHitPoints(0);
                } else {
                    // Else, the enemy dies and the score is updated
                    score += e1.getStrength();
                    e1.setHitPoints(0);
                }
            }

            // Check if enemy is dead
            if(!e1.isAlive()) {
                currentLevel.removeEnemy(e1);
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

    @Override
    public boolean activatePlayerAbility(int index) {
        IAbilityAction action = currentLevel.getPlayer().activateAbility(index);
        if(action != null) {
            activateAbility(action, System.nanoTime());
            return true;
        }
        return false;
    }

    @Override
    public void setPlayerFacingPosition(Point2D playerFacingPosition) {
        this.playerFacingPosition = playerFacingPosition;
    }

    // TODO: handle player death
    private void gameOver() {
        this.gameOver = true;
    }

    // Checks if an entity is out of bounds. Used for removing out of bounds projectiles
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

    //TODO: handle collision
    private void handleCollision(IEntity<?> e1, IEntity<?> e2){
    }
}



