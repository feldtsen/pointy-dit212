package game.controller.event;

import game.model.ability.action.IAbilityAction;

// Simple implementation of ability action event.
// The type of the event is only known by the object causing the ability action, and
// can not be deferred from the action itself.
// The action should be the actual object which caused the event (i.e the active ability action).
public class AbilityActionEvent implements IAbilityActionEvent {
    private final IAbilityActionEvent.Type type;
    private final IAbilityAction action;

    public AbilityActionEvent(IAbilityActionEvent.Type type, IAbilityAction action) {
        this.type = type;
        this.action = action;
    }

    public IAbilityActionEvent.Type getType() {
        return type;
    }

    public IAbilityAction getAction() {
        return action;
    }
}
