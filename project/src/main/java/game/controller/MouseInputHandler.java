package game.controller;

import game.model.ability.Ability;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class MouseInputHandler {
    private static MouseInputHandler INSTANCE = null; // Global instance

    private Point2D mousePosition;
    private final List<Action> onMove;
    private final List<Action> onLeftClick;
    private final List<Action> onRightClick;

    public static void init(Node node) {
        INSTANCE = new MouseInputHandler(node);
    }

    private MouseInputHandler(Node node) {
        this.onMove = new ArrayList<>();
        this.onLeftClick = new ArrayList<>();
        this.onRightClick = new ArrayList<>();

        node.setOnMouseMoved(mouseEvent -> {
            mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            for(Action a : onMove) {
                a.apply();
            }
        });
        node.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.isPrimaryButtonDown()) {
                for(Action a : onLeftClick) {
                    a.apply();
                }
            } else if(mouseEvent.isSecondaryButtonDown()) {
                for(Action a : onRightClick) {
                    a.apply();
                }
            }

        });
    }

    public static void registerActionOnMove(Action action) {
        INSTANCE.onMove.add(action);
    }
    public static void registerActionOnLeftClick(Action action) {
        INSTANCE.onLeftClick.add(action);
    }
    public static void registerActionOnRightClick(Action action) {
        INSTANCE.onRightClick.add(action);
    }

    public static Point2D getMousePosition() {
        return INSTANCE.mousePosition;
    }
}
