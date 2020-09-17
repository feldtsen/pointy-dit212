package game.model;

import game.model.entity.IEntity;

import java.util.List;

public class Level implements ILevel {

    private final List<IEntity<?>> enemies;
    private final List<IEntity<?>> projectiles;
    private final IEntity<?> player;
    private final double width;
    private final double height;


    public Level(List<IEntity<?>> enemies,List<IEntity<?>> projectiles, IEntity<?> player, int width, int height) {
        this.enemies = enemies;
        this.projectiles = projectiles;
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
