package game.controller;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

// This is a singleton class which handles keyboard input from the user.
// The class needs to be instantiated with the init method before any other methods are called.
public  class KeyboardInputHandler {
    private static KeyboardInputHandler INSTANCE = null; // Global instance

    // Set containing all the currently pressed keys.
    private final Set<KeyCode> pressedKeys;
    // Map of actions associated with each pressed key. If a key is pressed, its corresponding actions
    // will be applied on every call to "applyRegisteredActions".
    private final Map<KeyCode, List<Action>> actions;

    // Init feeds the input handler a stack pane which is required for setting up key pressed and key released callbacks.
    public static void init(Node node) {
        INSTANCE = new KeyboardInputHandler(node);
    }

    private KeyboardInputHandler(Node node) {
        pressedKeys = new ConcurrentSkipListSet<>();
        actions = new HashMap<>();

        // The register and unregister methods will be called on key press and key release, respectively.
        node.setOnKeyPressed(this::register);
        node.setOnKeyReleased(this::unregister);
    }

    // Simply adds a keycode to the set of currently pressed keys.
    private void register(KeyEvent e) {
        pressedKeys.add(e.getCode());
    }

    // Removes a keycode from the set of currently pressed keys.
    private void unregister(KeyEvent e){
        pressedKeys.remove(e.getCode());
    }

    // Registers an action to be performed on a key press.
    public static void registerAction(KeyCode code, Action action) {
        List<Action> list;

        // Check if there is no list of actions added for the current keycode.
        if(!INSTANCE.actions.containsKey(code)) {
            // Create a new array list, and add it to the actions map.
            list = new ArrayList<>();
            INSTANCE.actions.put(code, list);
        } else {
            // If a list already exist, fetch that one.
            list = INSTANCE.actions.get(code);
        }
        // add the action to the list
        list.add(action);
    }

    // Applies all actions associated with the currently pressed keys.
    public static void applyRegisteredActions() {
        // Loop over all currently pressed keys.
        for(KeyCode c : INSTANCE.pressedKeys) {
            List<Action> l = INSTANCE.actions.get(c);
            // If the currently pressed key has no actions associated with it, continue.
            if(l == null) continue;
            // Iterate over all actions, and apply.
            for(Action a : l) {
                a.apply();
            }
        }
    }
}
