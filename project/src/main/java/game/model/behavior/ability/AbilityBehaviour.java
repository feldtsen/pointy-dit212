package game.model.behavior.ability;

import game.model.ability.IAbility;

import java.util.List;

public abstract class AbilityBehaviour implements IAbilityBehaviour {
    private final List<IAbility> abilities;

    public AbilityBehaviour(List<IAbility> abilities) {
        this.abilities = abilities;
    }

    public List<IAbility> getAbilities() {
        return abilities;
    }
}
