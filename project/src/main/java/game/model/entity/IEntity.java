/*
 * Authors: Anton Hildingsson, Mattias Oom, Simon Genne
 *
 * Interface representing an object with a shape and position.
 * IEntity is parameterized, to enable the user of an entity to 
 * know which type of IShape2D each entity is.
 */

package game.model.entity;

import game.model.IPositionable;
import game.model.IUpdatable;
import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

public interface IEntity<T extends IShape2D> extends IPositionable, IUpdatable {
    
  // Checks collision with another entity
    <V extends IShape2D> Point2D checkCollision(IEntity<V> entity);
    
  // Returns the shape of the entity
    T getShape();
}
