/*
 * Authors: Erik Magnusson, Simon Genne, Mattias Oom, Anton Hildingsson
 */

package game.model.entity.obstacle;

import game.model.IUpdatable;
import game.model.entity.IEntity;
import game.model.entity.movable.IMovable;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;

// Interface marking all obstacles.
// An obstacle is a non-hostile entity on the map which typically blocks projectiles
// and/or entity movements
public interface IObstacle extends IEntity<Rectangle> {
    void handleCollision(Point2D minimumTranslationVector, IMovable<?> entity);
}
