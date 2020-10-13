package game.controller.event;

import game.model.ability.action.IAbilityAction;

// A simple event type used to indicate when an ability is activated
// or deactivated.
public interface IAbilityActionEvent {
    enum Type {
        ACTIVATED, // Used when an ability (action) is activated
        FINISHED,  // Used when an ability (action) is deactivated, or finished
    }
    // Returns the action which was activated or finished
    IAbilityAction getAction();
    // Returns the event type
    Type getType();
}
