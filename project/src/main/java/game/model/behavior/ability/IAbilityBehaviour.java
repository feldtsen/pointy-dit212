package game.model.behavior.ability;

import game.model.ability.action.IAbilityAction;
import game.model.behavior.IBehaviour;
import game.model.entity.IEntity;
import game.model.entity.movable.MovableEntity;
import game.model.level.ILevel;

public interface IAbilityBehaviour extends IBehaviour {
    IAbilityAction apply(ILevel level);
}
