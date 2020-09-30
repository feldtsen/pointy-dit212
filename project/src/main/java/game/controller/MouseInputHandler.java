package game.controller;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MouseInputHandler {
    private static MouseInputHandler INSTANCE = null; // Global instance

    private Point2D mousePosition;

    public MouseInputHandler init(Node node) {
        return new MouseInputHandler(node);
    }

    private MouseInputHandler(Node node) {
        node.setOnMouseMoved(mouseEvent -> {

            mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());



        });
    }

    public Point2D getMousePosition() {
        return mousePosition;
    }

    public static MouseInputHandler getInstance() {
        return INSTANCE;
    }
}
