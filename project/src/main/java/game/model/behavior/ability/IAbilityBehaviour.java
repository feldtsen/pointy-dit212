package game.model.behavior.ability;

import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.IBehaviour;
import game.model.entity.IEntity;

import java.util.List;

// An ability behavior defines how an entity will use it's abilities.
// Using this interface, more complex and intelligent usage of abilities can be defined.
public interface IAbilityBehaviour extends IBehaviour {
    // "user" is the entity which applies the ability, "target" is its target. Target may be null, of
    // an ability behavior has no particular target.
    IAbilityAction apply(IEntity<?> user, IEntity<?> target);

    List<IAbility> getAbilities();
}
