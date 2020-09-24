package game.model.behavior.ability;

import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.IBehaviour;
import game.model.entity.IEntity;
import game.model.entity.movable.MovableEntity;
import game.model.level.ILevel;

import java.util.List;

public interface IAbilityBehaviour extends IBehaviour {
    IAbilityAction apply(ILevel level);
    void setAbilities(List<IAbility> abilities);
}
