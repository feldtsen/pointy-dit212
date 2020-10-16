package game.model.entity;

import game.model.IPositionable;
import game.model.shape2d.IShape2D;

// Interface representing an object with a shape and position.
// IEntity is parameterized, to enable the user of an entity to know which type of IShape2D each entity is.
public interface IEntity<T extends IShape2D> extends IPositionable {
    // Checks collision with another entity
    <V extends IShape2D> boolean checkCollision(IEntity<V> entity);

    // Returns the shape of the entity
    T getShape();
}
