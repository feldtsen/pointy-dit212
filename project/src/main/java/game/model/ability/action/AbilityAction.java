package game.model.ability.action;

import game.model.level.ILevel;

// Abstract helper implementation of ability actions.
public abstract class AbilityAction implements IAbilityAction {
    private final double duration; // The duration (in seconds) when the ability is active

    public AbilityAction(double duration) {
        this.duration = duration;
    }

    // Implemented when the AbilityAction is created by different abilities.
    @Override
    public abstract void apply(ILevel level, double timePassed);

    @Override
    public double getDuration() {
        return duration;
    }
}
