/*
 * Authors: Anton Hildingsson, Mattias Oom 
 *  
 * Simple implementation of ability action event. Events should be sent by the handler
 * whenever an ability action is activated or finished.
 *
 * The type of the event is only known by the object causing the ability action, and
 * can not be deferred from the action itself. The action should be the actual object 
 * which caused the event (i.e the active ability action).
 */

package game.controller.event;

import game.model.ability.action.IAbilityAction;

public class AbilityActionEvent implements IAbilityActionEvent {
    // The type of event, either ACTIVATED or FINISHED
    private final IAbilityActionEvent.Type type;

    // The action which has been activated or just finished
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
