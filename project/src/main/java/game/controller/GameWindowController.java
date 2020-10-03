package game.controller;

import java.net.URL;
import java.util.ResourceBundle;

import game.model.Game;
import game.controller.gameLoop.GameLoop;
import game.controller.gameLoop.IGameLoop;
import game.model.IGame;
import game.view.Renderer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class GameWindowController implements Initializable {
    private IGame     game;     // Model
    private Renderer  renderer; // view
    private IGameLoop gameLoop;

    // Controllers
    private KeyboardInputController keyboardInputController;
    private MouseInputController mouseInputController;

    // Game pane is loaded from an FXML file, where the game view is created.
    @FXML
    private StackPane gamePane;

    // The canvas is also loaded from the same FXML file.
    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create a new renderer using the graphics context supplied by the canvas.
        renderer = new Renderer(canvas.getGraphicsContext2D());

        // Initialize the game and map all the keys to their corresponding actions.
        gameSetup();

        // Create a game loop. The update method will be called every frame.
        // Game loop is initialized with a improbably high desired fps value, to ensure the
        // game is run at max fps possible.
        gameLoop = new GameLoop(1000) {
            @Override
            public void update(double delta) {
                // Render the current level
                renderer.draw(game.getCurrentLevel());

                // Apply all registered keyboard actions
                keyboardInputController.applyRegisteredActions();

                // Update the game model with a global time step of 1 (normal speed)
                game.update(delta, 1);

                // Reinitialize game on player death
                // TODO: handle player death properly
                if(game.isGameOver()) gameSetup();
            }
        };

        // Start the game loop. At this point, the game is running.
        gameLoop.start();
    }

    //TODO: to implement
    @FXML
    private void handleMenuLevelButton () {
        System.out.println("Level button clicked");
    }

    //TODO: to implement
    @FXML
    private void handleMenuScoreButton() {
        System.out.println("Score button clicked ");
    }

    @FXML
    private void handleMenuStartButton() {
        gameLoop.setPaused(false);
        gamePane.lookup("#menuContainer").toBack();
        ((Button) gamePane.lookup("#menuStartButton")).setText("RETURN");
    }

    @FXML
    private void pauseGame() {
        gameLoop.setPaused(true);
        gamePane.lookup("#menuContainer").toFront();
    }

    private void gameSetup() {
        game = new Game();

        // Initialize the keyboard input handler.
        keyboardInputController = new KeyboardInputController(gamePane);
        // Initialize the mouse input handler
        mouseInputController = new MouseInputController(gamePane);

        // Give key codes registered by the game pane a given action
        keyboardInputController.registerAction(KeyCode.W, game.getCurrentLevel().getPlayer()::moveUp);
        keyboardInputController.registerAction(KeyCode.A, game.getCurrentLevel().getPlayer()::moveLeft);
        keyboardInputController.registerAction(KeyCode.S, game.getCurrentLevel().getPlayer()::moveDown);
        keyboardInputController.registerAction(KeyCode.D, game.getCurrentLevel().getPlayer()::moveRight);
        keyboardInputController.registerAction(KeyCode.ESCAPE, this::pauseGame);
        //KeyboardInputHandler.registerAction(KeyCode.E, () -> {
        //    game.activatePlayerAbility(0);
        //});

        // Register for mouse events
        mouseInputController.registerActionOnLeftClick(() -> game.activatePlayerAbility(0));
        mouseInputController.registerActionOnMove(     () -> game.setPlayerFacingPosition(mouseInputController.getMousePosition()));
    }
}
