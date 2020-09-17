package game.model.level;

import game.model.entity.IEntity;
import java.util.List;

public class LevelLoader {

    private final String path;

    public LevelLoader(String level) {
        this.path = "..." + level + ".csv";
    }


    public List<IEntity<?>> loadEnemies() {
        //TODO;
        return null;
    }

    public List<IEntity<?>> loadObstacles() {
        //TODO
        return null;
    }

    public IEntity<?> loadPlayer() {
        //TODO
        return null;
    }

    public double loadWidth() {
        //TODO
        return 0;
    }

    public double loadHeight() {
        //TODO
        return 0;
    }


}
