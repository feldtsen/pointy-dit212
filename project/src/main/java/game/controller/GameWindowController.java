package game.controller;

import java.net.URL;
import java.util.ResourceBundle;

import game.model.Game;
import game.controller.gameLoop.GameLoop;
import game.controller.gameLoop.IGameLoop;
import game.model.IGame;
import game.model.ability.action.IAbilityAction;
import game.view.Renderer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class GameWindowController implements Initializable {
    private IGameLoop gameLoop;
    private IGame game;
    private Renderer renderer;

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
                KeyboardInputHandler.applyRegisteredActions();

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
        KeyboardInputHandler.init(gamePane);
        // Initialize the mouse input handler
        MouseInputHandler.init(gamePane);

        // Give key codes registered by the game pane a given action
        KeyboardInputHandler.registerAction(KeyCode.W, game.getCurrentLevel().getPlayer()::moveUp);
        KeyboardInputHandler.registerAction(KeyCode.A, game.getCurrentLevel().getPlayer()::moveLeft);
        KeyboardInputHandler.registerAction(KeyCode.S, game.getCurrentLevel().getPlayer()::moveDown);
        KeyboardInputHandler.registerAction(KeyCode.D, game.getCurrentLevel().getPlayer()::moveRight);
        KeyboardInputHandler.registerAction(KeyCode.ESCAPE, this::pauseGame);
        //KeyboardInputHandler.registerAction(KeyCode.E, () -> {
        //    game.activatePlayerAbility(0);
        //});

        // Register for mouse events
        MouseInputHandler.registerActionOnLeftClick(() -> game.activatePlayerAbility(0));
        MouseInputHandler.registerActionOnMove(     () -> game.setPlayerFacingMouse(MouseInputHandler.getMousePosition()));
    }
}
