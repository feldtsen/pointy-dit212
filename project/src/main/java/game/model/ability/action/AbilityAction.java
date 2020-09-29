package game.model.ability.action;

import game.model.level.ILevel;

public abstract class AbilityAction implements IAbilityAction {
    private final double duration;

    public AbilityAction(double duration) {
        this.duration = duration;
    }

    // Implemented when the AbilityAction is created by Abilities.
    @Override
    public abstract void apply(ILevel level, double timePassed);

    public double getDuration() {
        return duration;
    }
}
