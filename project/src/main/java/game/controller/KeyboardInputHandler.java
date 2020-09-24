package game.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public  class KeyboardInputHandler {
    private static KeyboardInputHandler INSTANCE = null;

    public interface Action {
        void apply();
    }

    private final Set<KeyCode> pressedKeys;
    private final Map<KeyCode, List<Action>> actions;

    public static void init(StackPane gamePane) {
        INSTANCE = new KeyboardInputHandler(gamePane);
    }

    private KeyboardInputHandler(StackPane gamePane) {
        pressedKeys = new ConcurrentSkipListSet<>();
        actions = new HashMap<>();
        gamePane.setOnKeyPressed(this::register);
        gamePane.setOnKeyReleased(this::unregister);
    }

    private void register(KeyEvent e) {
        pressedKeys.add(e.getCode());
    }

    private void unregister(KeyEvent e){
        pressedKeys.remove(e.getCode());
    }

    public static void registerAction(KeyCode code, Action action) {
        List<Action> list;
        if(!INSTANCE.actions.containsKey(code)) {
            list = new ArrayList<>();
            INSTANCE.actions.put(code, list);
        } else {
            list = INSTANCE.actions.get(code);
        }
        list.add(action);
    }

    public static void applyRegisteredActions() {
        for(KeyCode c : INSTANCE.pressedKeys) {
            List<Action> l = INSTANCE.actions.get(c);
            if(l == null) continue;
            for(Action a : l) {
                a.apply();
            }
        }
    }
}
