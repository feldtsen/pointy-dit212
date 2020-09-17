package game.model;

import game.model.entity.IEntity;

import java.util.List;

public class Level implements ILevel {

    private List<IEntity> enemies;
    private List<IEntity> projectiles;
    private IEntity player;
    private int width;
    private int height;


    public Level(List<IEntity> enemies,List<IEntity> projectiles, IEntity player, int width, int height) {
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.player = player;
        this.width = width;
        this.height = height;
    }

    @Override
    public List<IEntity> getEnemies() {
        return this.enemies;
    }

    @Override
    public List<IEntity> getProjectiles() {
        return this.projectiles;
    }

    @Override
    public IEntity getPlayer() {
        return this.player;
    };

    @Override
    public int getWidth(){
        return this.width;
    }

    @Override
    public int getHeight(){
        return this.height;
    }


}
