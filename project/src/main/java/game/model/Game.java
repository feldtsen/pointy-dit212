/*
 * Authors: Anton Hildingsson, Erik Magnusson, Simon Genne, Mattias Oom, Joachim Pedersen
 */

package game.model;

import game.controller.event.AbilityActionEvent;
import game.controller.event.AbilityActionEventListener;
import game.controller.event.IAbilityActionEvent;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.enemy.IEnemy;
import game.model.entity.movable.IMovable;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;
import game.controller.gameLoop.GameLoop;
import game.model.level.ILevel;
import game.model.shape2d.ICircle;
import game.services.ILevelLoader;
import game.services.LevelLoader;
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

// Implementation of game interface.
// This is the core of the model, binding all the model parts together.
public class Game implements IGame {
    private int score; // Player score

    // True if the player has lost the game.
    private boolean gameOver;

    // True if game is completed.
    private boolean gameWin;

    // An iterator over the existing levels
    private ILevelLoader levelLoader;

    // The current, active level
    private ILevel currentLevel;

    // This value represents the position the player is "looking towards".
    //private Point2D playerFacingPosition;

    // This list is continuously updated with the active ability actions.
    // An action is removed when it's duration has run out.
    private final List<IAbilityAction> activeAbilityActions;

    // This is a helper list which contains "times" (in range [0, 1]) which represent the amount of time
    // an ability has been active
    private final List<Double> currentAbilityTimes;
    // This is a helper list containing the nano time of the activation of a certain ability.
    // Index n in this list corresponds to index n in activeAbilityActions.
    private final List<Long> activationTimes;

    // Ability action event listeners
    private final List<AbilityActionEventListener> listeners;

    public Game() {

        this.levelLoader = new LevelLoader("src/main/resources/game/levels/");
        this.currentLevel = levelLoader.getLevel();

        this.score = 0;
        this.gameOver = false;
        this.gameWin = false;

        this.activeAbilityActions = new ArrayList<>();
        this.currentAbilityTimes = new ArrayList<>();
        this.activationTimes = new ArrayList<>();

        listeners = new ArrayList<>();
    }

    // Loads the next level
    public void nextLevel() {
        if (levelLoader.hasNext()) {
            setLevel(levelLoader.next());
        }
        else {
            gameWin = true;
        }
    }

    // Activate ability adds an ability to the active ability actions list, together with the
    // corresponding activation time.
    private void activateAbility(IAbilityAction action, long now) {
        // Do nothing if action is null. Abilities often return null instead of an ability action when the
        // cooldown of an ability is active
        if (action == null) return;

        // Notify listeners for ability action events
        AbilityActionEvent event = new AbilityActionEvent(IAbilityActionEvent.Type.ACTIVATED, action);
        for (AbilityActionEventListener listener : listeners) {
            listener.onAction(event);
        }

        // Activate ability
        activeAbilityActions.add(action);

        // Set time to since started to 0
        currentAbilityTimes.add(0.0D);

        // Set nano time to the current time
        activationTimes.add(now);
    }

    // Deactivates (removes) an ability action at a particular index of the list.
    private void deactivateAbility(int index) {
        // Notify listeners for ability action events
        AbilityActionEvent event = new AbilityActionEvent(IAbilityActionEvent.Type.FINISHED, activeAbilityActions.get(index));
        for (AbilityActionEventListener listener : listeners) {
            listener.onAction(event);
        }

        // remove all data associated with this ability action
        activeAbilityActions.remove(index);
        currentAbilityTimes.remove(index);
        activationTimes.remove(index);
    }

    // Updates player and makes sure it is contained within the map
    private void updatePlayer(double delta, double timeStep) {
        IPlayer player = currentLevel.getPlayer();
        player.update(delta, timeStep);
        containToBounds(player); // Ensures the player cannot leave the map
    }

    // Updates all projectiles and checks for collisions with other entities
    private void updateProjectiles(double delta, double timeStep) {
        IPlayer player = currentLevel.getPlayer();

        // Check for collisions between player and projectiles. Adjust players hit points if collision occurs.
        // Iterates backwards to enable removing projectiles from the list at the same time as looping.
        for (int i = currentLevel.getProjectiles().size() - 1; i >= 0; i--) {
            IProjectile<?> projectile = currentLevel.getProjectiles().get(i);

            // Update projectile
            projectile.update(delta, timeStep);

            // Check collision with player, and reduce player hit points if collision.
            if (player.checkCollision(projectile) != null && !player.isInvulnerable()) {
                player.setHitPoints(player.getHitPoints() - projectile.getStrength());

                // Destroy projectile on hit
                projectile.setDestroyed();
            }

            // Iterate over all the enemies
            for (IEnemy enemy : getCurrentLevel().getEnemies()) {
                // Check collision with projectiles
                if (projectile.checkCollision(enemy) != null) {
                    // If the strength of the projectiles is grater than that of the enemies
                    if (projectile.getStrength() > enemy.getStrength()) {
                        // If the enemy dies the score is updated
                        score += enemy.getStrength();
                        enemy.setHitPoints(0);
                        // Set destroyed on hit
                        projectile.setDestroyed();
                    }
                }
            }

            // Iterate over all obstacles and check for collision with projectiles
            for (IObstacle obstacle : getCurrentLevel().getObstacles()) {
                if (projectile.checkCollision(obstacle) != null) {
                    // set destroyed on collision
                    projectile.setDestroyed();
                }
            }

            // Remove projectile from list if destroyed or out of bounds
            if (projectile.isDestroyed() || isOutOfBounds(projectile)) {
                currentLevel.getProjectiles().remove(i);
            }
        }
    }

    // Update enemies
    private void updateEnemies(double delta, double timeStep, long now) {
        for (IEnemy enemy : currentLevel.getEnemies()) {
            enemy.update(delta, timeStep);
            containToBounds(enemy); // Ensure enemies cannot leave map.

            // Activate enemy abilities
            // If cooldown is active, no ability action will be returned, and this method call will do nothing
            activateAbility(enemy.applyAbility(), now);
        }
    }

    // Update obstacles
    private void updateObstacles(double delta, double timeStep) {
        IPlayer player = currentLevel.getPlayer();

        // Update all obstacles and check for collision with player.
        for(IObstacle obstacle:currentLevel.getObstacles()) {
            obstacle.update(delta, timeStep);

            // Check if collision has occurred and get minimum translation vector.
            Point2D minimumTranslationVector = player.checkCollision(obstacle);
            if (minimumTranslationVector != null) {
                obstacle.handleCollision(minimumTranslationVector, player);
            }
        }
    }

    // Apply active abilities
    private void applyAbilityActions(long now) {
        for(int i = 0; i < activeAbilityActions.size(); i++) {
            IAbilityAction abilityAction = activeAbilityActions.get(i);
            long activationTime = activationTimes.get(i);

            // Normalized time value representing how long an ability action has been active
            double time = (now - activationTime) / (GameLoop.SECOND * abilityAction.getDuration());
            currentAbilityTimes.set(i, time);

            // Feed the ability the time since activation. Some ability will depend on this value.
            abilityAction.apply(currentLevel, time);
        }
    }

    // Handle enemy-related collisions
    private void handleEnemyCollisions() {
        IPlayer player = currentLevel.getPlayer();
        // Check collision between players and enemies, and enemies and other enemies
        for (int i = 0; i < currentLevel.getEnemies().size(); i++) {
            IEnemy enemy1 = currentLevel.getEnemies().get(i);
            // Loop from i + 1 to ensure collision is not checked twice for each entity pair, and to avoid checking
            // self collision checking.
            for (int j = i + 1; j < currentLevel.getEnemies().size(); j++){
                IEnemy enemy2 = currentLevel.getEnemies().get(j);

                // check if collision has occurred and get minimum translation vector.
                Point2D minimumTranslationVector = enemy1.checkCollision(enemy2);
                if (minimumTranslationVector != null) {

                    // Apply half of the translation vector on one enemy and half on the other in the opposite direction.
                    enemy1.move(Utils.setMagnitude(minimumTranslationVector, minimumTranslationVector.magnitude() / 2));
                    enemy2.move(Utils.setMagnitude(minimumTranslationVector, minimumTranslationVector.magnitude() / 2 * (-1)));
                }
            }

            // Check player-enemy collision
            if (player.checkCollision(enemy1) != null){

                // If enemy is stronger than player, player dies
                if (player.getStrength() < enemy1.getStrength() && !player.isInvulnerable()){
                    player.setHitPoints(0);
                }
                else if (player.getStrength() > enemy1.getStrength()) {
                    // Else, the enemy dies and the score is updated
                    score += enemy1.getStrength();
                    enemy1.setHitPoints(0);
                }
            }
            // Check enemy-obstacle colllision
            for (IObstacle obstacle: getCurrentLevel().getObstacles()){
                Point2D minimumTranslationVector = enemy1.checkCollision(obstacle);
                if (minimumTranslationVector != null) {
                    obstacle.handleCollision(minimumTranslationVector, enemy1);
                }
            }

            // Check if enemy is dead
            if(!enemy1.isAlive()) {
                currentLevel.removeEnemy(enemy1);
            }
        }
    }

    // Removes finished ability actions
    private void removeFinishedAbilityActions(long now) {
        // Iterate backwards to avoid problems with removing entries from list
        for(int i = activeAbilityActions.size() - 1;  i >= 0; i--) {
            IAbilityAction abilityAction = activeAbilityActions.get(i);
            long activationTime = activationTimes.get(i);
            // Check if the time since activation time exceeds the duration of the abilityAction.
            if(now - activationTime >= abilityAction.getDuration() * GameLoop.SECOND) {
                // If true, deactivate ability.
                abilityAction.onFinished(currentLevel); //Method called for cleanup
                deactivateAbility(i);
            }
        }
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
        updatePlayer(delta, timeStep);

        // Update projectiles
        updateProjectiles(delta, timeStep);

        // Update all enemies
        updateEnemies(delta, timeStep, now);

        // Update obstacles
        updateObstacles(delta, timeStep);

        // Apply active abilities
        applyAbilityActions(now);

        // Handle enemy-enemy collisions, enemy-player collisions and enemy-obstacle collisions
        handleEnemyCollisions();

        // Remove finished ability actions
        removeFinishedAbilityActions(now);
    }

    // Activates the player ability. This method is called from outside the game,
    // usually by a controller triggered by user input.
    @Override
    public boolean activatePlayerAbility(int index) {
        IAbilityAction action = currentLevel.getPlayer().activateAbility(index);
        if(action != null) {
            activateAbility(action, System.nanoTime());
            return true;
        }
        return false;
    }

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
        Point2D velocity = entity.getVelocity();

        Point2D position = entity.getPosition();
        double radius = entity.getShape().getRadius();

        // Collision with left wall
        if(position.getX() - radius < 0) {
            position = new Point2D(radius, position.getY());
            velocity = new Point2D(0, velocity.getY());
        // Collision with right wall
        } else if(position.getX() + radius >= width) {
            position = new Point2D(width - radius, position.getY());
            velocity = new Point2D(0, velocity.getY());
        }
        // Collision with top wall
        if(position.getY() - radius < 0) {
            position = new Point2D(position.getX(), radius);
            velocity = new Point2D(velocity.getX(), 0);
        // Collision with bottom wall
        } else if(position.getY() + radius >= height) {
            position = new Point2D(position.getX(), height - radius);
            velocity = new Point2D(velocity.getX(), 0);
        }

        entity.setPosition(position);
        entity.setVelocity(velocity);
    }

    // Sets the current level
    @Override
    public boolean setLevel(ILevel level) {
        this.currentLevel = level;
        return true;
    }

    @Override
    public ILevel getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public List<IAbilityAction> getActiveAbilityActions() {
        return activeAbilityActions;
    }

    @Override
    public List<Double> getActiveAbilityTimes() { return currentAbilityTimes; }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public boolean isGameWin() {
        return gameWin;
    }

    // Registers listeners for ability action events
    @Override
    public void registerListener(AbilityActionEventListener listener) {
        listeners.add(listener);
    }
}



