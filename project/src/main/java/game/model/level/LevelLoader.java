package game.model.level;

import game.model.entity.IEntity;
import java.util.List;

public class LevelLoader {

    private final String level;

    public LevelLoader(String level) {
        this.level = level;
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
