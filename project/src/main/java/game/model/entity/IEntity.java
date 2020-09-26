package game.model.entity;

import game.model.IPositionable;
import game.model.shape2d.IShape2D;

// Interface representing an object with a shape and position.
public interface IEntity<T extends IShape2D> extends IPositionable {
    <V extends IShape2D> boolean checkCollision(IEntity<V> entity);
    T getShape();
}
