/*
 * Authors: Anton Hildingsson 
 *
 * Helper interface for defining an action to be performed on an event. 
 * Typically used as a lambda expression, such as "() -> [code to execute]"
 * This interface is used in the mouse and keyboard input controllers.
 */

package game.controller;

public interface IAction {
    // This method is called by the controller which executes the action.
    void apply();
}
