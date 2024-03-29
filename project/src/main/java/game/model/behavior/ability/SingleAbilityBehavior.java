/*
 * Authors: Anton Hildingsson
 *
 * Defines a simple ability behavior with only one ability, which is always applied.
 * Only one ability is used, and each time apply is called, the ability will be activated.
 * However, if the ability cooldown is active, null will be returned.
 */

package game.model.behavior.ability;

import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;

import java.util.List;

public class SingleAbilityBehavior extends AbilityBehaviour {
    // Constructor creates a list with a single element
    public SingleAbilityBehavior(IAbility ability) {
        super(List.of(ability));
    }

    @Override
    public IAbilityAction apply(IEntity<?> user, IEntity<?> target) {
        return getAbilities().get(0).use(user, target);
    }
}
