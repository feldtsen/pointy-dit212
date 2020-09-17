package game.model.level;

import game.model.entity.IEntity;
import java.util.List;

public class LevelLoader implements ILevelLoader {

    private final String path;

    public LevelLoader(String levelID) {
        this.path = "..." + levelID + ".csv";
    }


    @Override
    public List<IEntity<?>> loadEnemies() {
        //TODO;
        return null;
    }

    @Override
    public List<IEntity<?>> loadObstacles() {
        //TODO
        return null;
    }

    @Override
    public IEntity<?> loadPlayer() {
        //TODO
        return null;
    }

    @Override
    public double loadWidth() {
        //TODO
        return 0;
    }

    @Override
    public double loadHeight() {
        //TODO
        return 0;
    }


}
