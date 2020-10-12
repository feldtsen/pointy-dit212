package game.controller.event;

import game.model.ability.action.IAbilityAction;

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
