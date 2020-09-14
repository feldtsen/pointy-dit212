package game.model;

import game.model.entity.IEntity;

public interface IBehaviour {

    /**
    * Applies the behaviour of one entity to another entity.
    * @param subject the entity which performs the behaviour, object the entitiy which the behaviour is being performed on.
     */
    boolean apply(IEntity subject, IEntity object);
}