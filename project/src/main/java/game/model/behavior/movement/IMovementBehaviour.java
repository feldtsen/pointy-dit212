package game.model.behavior.movement;

import game.model.behavior.IBehaviour;
import game.model.entity.IEntity;
import game.model.entity.movable.MovableEntity;

public interface IMovementBehaviour extends IBehaviour {
    /**
     * Applies the behaviour of one entity to another entity.
     * @param subject the entity which performs the behaviour, object the entity which the behaviour is being performed on.
     */
    boolean apply(MovableEntity<?> subject, IEntity<?> object);
}
