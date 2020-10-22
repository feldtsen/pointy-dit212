/*
 * Authors: Erik Magnusson, Anton Hildingsson, Mattias Oom, Joachim Pedersen
 *
 * This interface represents a single level of the game, and manages all entities.
 * A level holds the player, the enemies, all projectiles and obstacles, and 
 * supplies the game with a few methods for managing these entities.
 */

package game.model.level;

import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;

import java.util.List;

public interface ILevel {
    // The player of the level. Different for each level, however
    // player controls should always affect the different players the same (unless the players have different
    // abilities).
    IPlayer getPlayer();

    // All enemies in the current level
    List<IEnemy> getEnemies();
    // All projectiles in the current level
    List<IProjectile<?>> getProjectiles();
    // All obstacles in the current level
    List<IObstacle> getObstacles();

    // Methods for removing entities from the level.
    // This is usually done when an entity is destroyed or killed
    void removeEnemy(IEnemy enemy);
    void removeObstacle(IObstacle obstacle);
    void removeProjectile(IProjectile<?> projectile);

    // The dimensions of the level
    double getWidth();
    double getHeight();

    // Retrieve the current level number
    int getLevelNr();

    void setWidth(double width);
    void setHeight(double height);

}
