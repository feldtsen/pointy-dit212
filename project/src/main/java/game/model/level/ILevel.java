package game.model.level;

import game.model.entity.IEntity;
import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;

import java.util.List;

public interface ILevel {
    List<IEntity<?>> getEnemies();
    List<IEntity<?>> getProjectiles();
    List<IEntity<?>> getObstacles();
    IEntity<?> getPlayer();
    double getWidth();
    double getHeight();

}
