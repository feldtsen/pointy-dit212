package game.controller;

import game.model.entity.player.Player;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public  class UserInputController {
    //TODO: user inputs can be singleton!

    //TODO: does not need to be instantiated, gamePane can register event listening once on game startup!

    //TODO: more generic solution: register "actions" for each keypress. Call update every frame, on each update,
    //TODO: look through list of currently held keys (hashset?), see if action is registered for that keypress, activate aciton!

    //TODO: alternative: each action associated with key, each frame check if associated key is pressed! if pressed, activate action!

    //TODO: game handles actions, ex UserInputController has method called "setOnHeld(KeyCode code, Lambda...)"
    //TODO: example call: UserInputController.setOnHeld("W", () -> player.moveUp())

    //TODO: UserInputController has update method, calls every lambda expression each frame if corresponding key is pressed

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
