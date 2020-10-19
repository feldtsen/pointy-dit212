/*
 * Authors: Anton Hildingsson, Mattias Oom
 */

package game.model.behavior.movement;

import game.model.behavior.IBehaviour;
import game.model.entity.IEntity;
import game.model.entity.movable.IMovable;

// A behavior class for defining different kinds of movement. Used by enemies and certain projectiles.
public interface IMovementBehaviour extends IBehaviour {

    // Applies the behaviour of one entity to another entity.
    // subject is the entity which performs the behaviour, object is the entity which the behaviour is being performed on.
    boolean apply(IMovable<?> subject, IEntity<?> object);
}
