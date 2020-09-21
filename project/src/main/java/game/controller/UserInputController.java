package game.controller;

import game.model.player.Player;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public  class UserInputController {
    private final boolean[] directions = new boolean[]{false, false, false, false};
    private final Player player;
    StackPane gamePane;

    public UserInputController(StackPane gamePane, Player player) {
        this.gamePane = gamePane;
        this.player = player;
        gamePane.setOnKeyPressed(this::register);
        gamePane.setOnKeyReleased(this::unregister);
    }

    private void setDirections (KeyEvent e, boolean isPressed) {
        switch (e.getCode()) {
            case W:
                directions[0] = isPressed;
                break;
            case A:
                directions[1] = isPressed;
                break;
            case S:
                directions[2] = isPressed;
                break;
            case D:
                directions[3] = isPressed;
                break;

        }

    }

    private void register(KeyEvent e) {
        setDirections(e, true);
    }

    private void unregister(KeyEvent e){
        setDirections(e, false);
    }

    public void movePlayer() {
        if(directions[0]) player.moveUp();
        if(directions[1]) player.moveLeft();
        if(directions[2]) player.moveDown();
        if(directions[3]) player.moveRight();
    }

}
