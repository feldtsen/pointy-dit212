package game.model.ability;

import java.util.List;

public abstract class AbilityBehaviour implements IAbilityBehaviour {
    private List<IAbility> abilities;

    public AbilityBehaviour(List<IAbility> abilities) {
        this.abilities = abilities;
    }

    public List<IAbility> getAbilities() {
        return abilities;
    }
}
