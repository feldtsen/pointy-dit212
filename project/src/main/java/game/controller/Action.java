/*
 * Authors: Anton Hildingsson 
 */

package game.controller;

// Helper interface for defining an action to be performed on an event
// Typically used as a lambda expression, such as "() -> [code to execute]"
public interface Action {
    // This method is called by the controller which executes the action.
    void apply();
}
