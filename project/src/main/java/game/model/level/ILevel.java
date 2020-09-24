package game.model.level;

import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.player.Player;
import game.model.entity.projectile.IProjectile;
import game.model.shape2d.ICircle;

import java.util.List;

public interface ILevel {
    List<Enemy> getEnemies();
    List<IProjectile<?>> getProjectiles();
    List<IObstacle> getObstacles();
    Player getPlayer();
    double getWidth();
    double getHeight();

    void removeEnemy(Enemy e);
    void removeObstacle(IObstacle o);
    void removeProjectile(IProjectile<?> p);

}
