package game.model.level;

import game.model.entity.IEntity;
import game.model.level.ILevel;

import java.util.ArrayList;
import java.util.List;

public class Level implements ILevel {

    private final List<IEntity<?>> enemies;
    private final List<IEntity<?>> projectiles;
    private final List<IEntity<?>> obstacles;
    private final IEntity<?> player;
    private final double width;
    private final double height;



    public Level(String level) {
        LevelLoader ll = new LevelLoader(level);
        this.enemies = ll.loadEnemies();
        this.projectiles = new ArrayList<>();
        this.obstacles = ll.loadObstacles();
        this.player = ll.loadPlayer();
        this.width = ll.loadWidth();
        this.height = ll.loadHeight();
    }

    @Override
    public List<IEntity<?>> getEnemies() {
        return this.enemies;
    }

    @Override
    public List<IEntity<?>> getProjectiles() {
        return this.projectiles;
    }

    @Override
    public List<IEntity<?>> getObstacles() {
        return this.obstacles;
    }

    @Override
    public IEntity<?> getPlayer() {
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


}
