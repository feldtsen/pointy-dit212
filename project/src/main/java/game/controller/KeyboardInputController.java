/*
 * Authors: Anton Hildingsson, Joachim Pedersen
 */

package game.controller;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

// This is a singleton class which handles keyboard input from the user.
// The class needs to be instantiated with the init method before any other methods are called.
public  class KeyboardInputController {
    // Set containing all the currently pressed keys.
    private final Set<KeyCode> pressedKeys;
    // Map of actions associated with each pressed key. If a key is pressed, its corresponding actions
    // will be applied on every call to "applyRegisteredActions".
    private final Map<KeyCode, List<Action>> heldActions;
    private final Map<KeyCode, List<Action>> pressedActions;

    // Feeds the input handler a stack pane which is required for setting up key pressed and key released callbacks.
    public KeyboardInputController(Node node) {
        pressedKeys = new ConcurrentSkipListSet<>();
        heldActions = new HashMap<>();
        pressedActions = new HashMap<>();

        // The register and unregister methods will be called on key press and key release, respectively.
        // Simply adds a keycode to the set of currently pressed keys.
        node.setOnKeyPressed(e ->   {
            pressedKeys.add(e.getCode());
        });
        // Removes a keycode from the set of currently pressed keys.
        node.setOnKeyReleased(e -> {
            pressedKeys.remove(e.getCode());
            applyPressedRegisteredActions(e.getCode());
        });
    }


    public void registerHeldAction(KeyCode code, Action action) {
        registerAction(code, action, heldActions);

    }

    public void registerPressedAction(KeyCode code, Action action) {
        registerAction(code, action, pressedActions);
    }


        // Registers an action to be performed on a key press.
    public void registerAction(KeyCode code, Action action, Map<KeyCode, List<Action>> actions) {
        List<Action> list;

        // Check if there is no list of actions added for the current keycode.
        if(!heldActions.containsKey(code)) {
            // Create a new array list, and add it to the actions map.
            list = new ArrayList<>();
            actions.put(code, list);
        } else {
            // If a list already exist, fetch that one.
            list = actions.get(code);
        }
        // add the action to the list
        list.add(action);
    }

    public void applyPressedRegisteredActions(KeyCode code) {
        if (pressedActions.containsKey(code))
            pressedActions.get(code).get(0).apply();

    }

    // Applies all actions associated with the currently pressed keys.
    public void applyHeldRegisteredActions() {
        // Loop over all currently pressed keys.
        for(KeyCode c : pressedKeys) {
            List<Action> l = heldActions.get(c);
            // If the currently pressed key has no actions associated with it, continue.
            if(l == null) continue;
            // Iterate over all actions, and apply.
            for(Action a : l) {
                a.apply();
            }
        }
    }
}
