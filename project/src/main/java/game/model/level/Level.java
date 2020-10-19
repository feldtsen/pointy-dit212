/*
Author: Mattias Oom, Joachim Ørfeldt Pedersen
 */
package game.model.level;

import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;
import game.util.Timer;

import java.util.List;



//Holds all relevant data about a level.
//Used by "Game" class
public class Level implements ILevel {
    // All enemies in the level
    private final List<IEnemy> enemies;

    // All projectiles spawned. This list typically starts out empty but is filled
    // as enemies spawn projectiles during gameplay
    private final List<IProjectile<?>> projectiles;

    // All obstacles
    private final List<IObstacle> obstacles;

    // The player
    private final IPlayer player;

    // Level dimensions
    private double width;
    private double height;


    public Level(List<IEnemy> enemies, List<IProjectile<?>> projectiles, List<IObstacle> obstacles, IPlayer player, double width, double height) {
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.obstacles = obstacles;
        this.player = player;
        this.width = width;
        this.height = height;
    }

    @Override
    public List<IEnemy> getEnemies() {
        return this.enemies;
    }

    @Override
    public List<IProjectile<?>> getProjectiles() {
        return this.projectiles;
    }

    @Override
    public List<IObstacle> getObstacles() {
        return this.obstacles;
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    };
    

    @Override
    public double getWidth(){
        return this.width;
    }

    @Override
    public double getHeight(){
        return this.height;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void setHeight(double height) {
       this.height = height;
    }

    @Override
    public void removeEnemy(IEnemy enemy) {
        enemies.remove(enemy);
    }

    @Override
    public void removeObstacle(IObstacle obstacle) {
        obstacles.remove(obstacle);
    }

    @Override
    public void removeProjectile(IProjectile<?> projectile) {
        projectiles.remove(projectile);
    }



}
