package game.model.entity;

import game.model.IPositionable;
import game.model.shape2d.IShape2D;

public interface IEntity<T extends IShape2D> extends IPositionable {
    boolean checkCollision(IEntity<?> entity);
    T getShape();
    void update(double delta);
}
