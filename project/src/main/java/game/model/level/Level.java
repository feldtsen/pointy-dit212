package game.model.level;

import game.model.entity.IEntity;

import java.util.ArrayList;
import java.util.List;

public class Level implements ILevel {

    private final List<IEntity<?>> enemies;
    private final List<IEntity<?>> projectiles;
    private final List<IEntity<?>> obstacles;
    private final IEntity<?> player;
    private final double width;
    private final double height;



    public Level(List<IEntity<?>> enemies, List<IEntity<?>> projectiles, List<IEntity<?>> obstacles, IEntity<?> player, double width, double height) {
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.obstacles = obstacles;
        this.player = player;
        this.width = width;
        this.height = height;
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
