package game.model;

import game.model.entity.IEntity;

import java.util.List;

public interface ILevel {
    List<IEntity> getEnemies();
    List<IEntity> getProjectiles();
    IEntity getPlayer();
    int getWidth();
    int getHeight();

}
