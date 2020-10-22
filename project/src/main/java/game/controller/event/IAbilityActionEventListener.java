/*
 * Authors: Anton Hildingsson, Mattias Oom
 *
 * Interface for any class listening for ability action events
 * On action should be called by any ability action event handler when
 * an ability action event occurs.
 */

package game.controller.event;

// Interface for any class listening for ability action events
// On action should be called by any ability action event handler when
// an ability action event occurs.
public interface IAbilityActionEventListener {
    // This method is called whenever an ability action event occurs
    void onAction(IAbilityActionEvent event);
}
