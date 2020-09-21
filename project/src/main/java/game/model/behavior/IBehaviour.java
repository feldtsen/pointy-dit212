package game.model.behavior;

import game.model.entity.IEntity;

public interface IBehaviour<T extends IEntity<?>, V extends IEntity<?>> {

    /**
    * Applies the behaviour of one entity to another entity.
    * @param subject the entity which performs the behaviour, object the entitiy which the behaviour is being performed on.
     */
    boolean apply(T subject, V object);
}