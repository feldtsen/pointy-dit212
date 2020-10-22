/*
 * Authors: Anton Hildingsson, Joachim Pedersen 
 *
 * Handles mouse input from the user. Different actions can
 * be registered to be executed on mouse move, left click or right click.
 *
 * The mouse position value is contiuously updated when the mouse
 * position changes.
 *
 */

package game.controller;

import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class MouseInputController {
    private Point2D mousePosition;

    // Actions to be performed on different mouse events
    private final List<Action> onMove;
    private final List<Action> onLeftClick;
    private final List<Action> onRightClick;

    public MouseInputController(Node node) {
        this.onMove = new ArrayList<>();
        this.onLeftClick = new ArrayList<>();
        this.onRightClick = new ArrayList<>();

        // Register lambda functions as callbacks on mouse events
        node.setOnMouseMoved(mouseEvent -> {
            // Update mouse position on mouse move
            mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            // Apply all on move actions
            for(Action a : onMove) {
                a.apply();
            }
        });
        node.setOnMousePressed(mouseEvent -> {
            // Apply all left click actions
            if(mouseEvent.isPrimaryButtonDown()) {
                for(Action a : onLeftClick) {
                    a.apply();
                }
            // Apply all right click actions
            } else if(mouseEvent.isSecondaryButtonDown()) {
                for(Action a : onRightClick) {
                    a.apply();
                }
            }
        });
    }

    // Register actions on different mouse actions
    public void registerActionOnMove(Action action) {
        onMove.add(action);
    }
    public void registerActionOnLeftClick(Action action) {
        onLeftClick.add(action);
    }
    public void registerActionOnRightClick(Action action) {
        onRightClick.add(action);
    }

    // Returns the current position of the mouse, relative to the application window
    public Point2D getMousePosition() {
        return mousePosition;
    }
}
