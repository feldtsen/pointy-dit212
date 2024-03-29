/*
 * Authors: Anton Hildingsson, Mattias Oom
 *
 * Abstract helper implementation of ability actions.
 * When creating an ability action, this class is typically extended, 
 * to simplify implementation.
 */

package game.model.ability.action;

import game.model.entity.IEntity;
import game.model.level.ILevel;

public abstract class AbilityAction implements IAbilityAction {
    // The user of the ability action
    protected final IEntity<?> user;

    // The duration (in seconds) when the ability is active
    private final double duration;

    public AbilityAction(IEntity<?> user, double duration) {
        this.user = user;
        this.duration = duration;
    }

    // Implemented when the AbilityAction is created by different abilities.
    @Override
    public abstract void apply(ILevel level, double timePassed);

    @Override
    public double getDuration() {
        return duration;
    }

    @Override
    public IEntity<?> getUser() {
        return user;
    }
}
