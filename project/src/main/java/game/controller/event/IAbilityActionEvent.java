package game.controller.event;

import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;

public interface IAbilityActionEvent {
    enum Type {
        ACTIVATED,
        FINISHED,
    }
    IAbilityAction getAction();
    Type getType();
}
