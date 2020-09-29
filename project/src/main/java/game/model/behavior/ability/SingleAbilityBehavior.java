package game.model.behavior.ability;

import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;

import java.util.List;

// Defines a simple ability behavior with only one ability, which is always applied.
public class SingleAbilityBehavior extends AbilityBehaviour {
    public SingleAbilityBehavior(IAbility ability) {
        super(List.of(ability));
    }

    @Override
    public IAbilityAction apply(IEntity<?> user, IEntity<?> target) {
        return getAbilities().get(0).use(user, target);
    }
}
