/*
 * Authors: Mattias Oom, Anton Hildingsson, Simon Genne
 *
 * Abstract ability behavior implementation. Defines a set of abilities to be applied.
 */

package game.model.behavior.ability;

import game.model.ability.IAbility;

import java.util.List;

public abstract class AbilityBehaviour implements IAbilityBehaviour {
    // List of abilities to be used
    private final List<IAbility> abilities;

    public AbilityBehaviour(List<IAbility> abilities) {
        this.abilities = abilities;
    }

    @Override
    public List<IAbility> getAbilities() {
        return abilities;
    }
}
