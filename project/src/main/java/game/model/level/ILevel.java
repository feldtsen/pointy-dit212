package game.model.level;

import game.model.entity.IEntity;

import java.util.List;

public interface ILevel {
    List<IEntity<?>> getEnemies();
    List<IEntity<?>> getProjectiles();
    List<IEntity<?>> getObstacles();
    IEntity<?> getPlayer();
    double getWidth();
    double getHeight();

}
