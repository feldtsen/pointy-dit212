/*
 * Authors: Anton Hildingsson, Joachim Pedersen 
 */

package game.controller;

import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

// Singleton class for handling mouse events.
// This class needs to be instantiated by calling init before calling any other methods.
public class MouseInputController {
    private Point2D mousePosition;

    // Actions to be performed on different mouse events
    private final List<IAction> onMove;
    private final List<IAction> onLeftClick;
    private final List<IAction> onRightClick;

    public MouseInputController(Node node) {
        this.onMove = new ArrayList<>();
        this.onLeftClick = new ArrayList<>();
        this.onRightClick = new ArrayList<>();

        // Register lambda functions as callbacks on mouse events
        node.setOnMouseMoved(mouseEvent -> {
            // Update mouse position on mouse move
            mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            // Apply all on move actions
            for(IAction a : onMove) {
                a.apply();
            }
        });
        node.setOnMousePressed(mouseEvent -> {
            // Apply all left click actions
            if(mouseEvent.isPrimaryButtonDown()) {
                for(IAction a : onLeftClick) {
                    a.apply();
                }
            // Apply all right click actions
            } else if(mouseEvent.isSecondaryButtonDown()) {
                for(IAction a : onRightClick) {
                    a.apply();
                }
            }
        });
    }

    // Register actions on different mouse actions
    public void registerActionOnMove(IAction action) {
        onMove.add(action);
    }
    public void registerActionOnLeftClick(IAction action) {
        onLeftClick.add(action);
    }
    public void registerActionOnRightClick(IAction action) {
        onRightClick.add(action);
    }

    // Returns the current position of the mouse, relative to the application window
    public Point2D getMousePosition() {
        return mousePosition;
    }
}
