/*
 * Authors: Anton Hildingsson, Mattias Oom
 *
 * A behavior class for defining different kinds of movement. Used by enemies and certain projectiles.
 * The subject in the apply method should be the entity using this behavior, while the object could be n
 * null, or the target of the entity.
 */

package game.model.behavior.movement;

import game.model.behavior.IBehaviour;
import game.model.entity.IEntity;
import game.model.entity.movable.IMovable;

public interface IMovementBehaviour extends IBehaviour {

    // Applies the behaviour of one entity to another entity.
    // subject is the entity which performs the behaviour, object is the entity which the behaviour is being performed on.
    boolean apply(IMovable<?> subject, IEntity<?> object);
}
