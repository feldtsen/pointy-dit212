/*
 * Authors: Anton Hildingsson, Mattias Oom 
 *
 * A simple event type used to indicate when an ability is activated. 
 * The ability action event handler should send an event whenever an 
 * ability is activated or just finished.
 */

package game.controller.event;

import game.model.ability.action.IAbilityAction;

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
