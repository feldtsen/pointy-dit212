package game.model.level;

import game.model.entity.IEntity;

import java.util.List;

public interface ILevelLoader {

    List<IEntity<?>> loadEnemies();
    List<IEntity<?>> loadObstacles();
    IEntity<?> loadPlayer();
    double loadWidth();
    double loadHeight();

}
