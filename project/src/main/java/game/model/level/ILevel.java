package game.model.level;

import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;

import java.util.List;

// This interface represents a single level of the game, and manages all entities.
public interface ILevel {
    IPlayer getPlayer();
    List<IEnemy> getEnemies();
    List<IProjectile<?>> getProjectiles();
    List<IObstacle> getObstacles();

    void removeEnemy(IEnemy e);
    void removeObstacle(IObstacle o);
    void removeProjectile(IProjectile<?> p);

    double getWidth();
    double getHeight();

    void setWidth(double width);
    void setHeight(double height);
}
