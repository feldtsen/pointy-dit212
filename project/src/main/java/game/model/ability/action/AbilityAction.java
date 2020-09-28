package game.model.ability.action;

import game.model.entity.IEntity;
import game.model.level.ILevel;

public abstract class AbilityAction implements IAbilityAction{
    private IEntity<?> user; // The user of the ability.
    private IEntity<?> target; // The target of the ability (also the user in cases like shockwave etc.).
    private double duration;

    public AbilityAction(IEntity<?> user, IEntity<?> target, double duration) {
        this.user = user;
        this.target = target;
        this.duration = duration;
    }

    // Implemented when the AbilityAction is created by Abilities.
    @Override
    public abstract void apply(ILevel level, double timePassed);

    public double getDuration() {
        return duration;
    }
}
