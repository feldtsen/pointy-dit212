package game.controller;

import game.model.Game;
import game.controller.gameLoop.GameLoop;
import game.controller.gameLoop.IGameLoop;
import game.model.IGame;
import game.view.pages.MainWindow;
import game.view.pages.canvas.GameCanvas;
import game.view.renderer.Renderer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GameWindowController {
    private IGame     game;     // Model
    private final Renderer  renderer; // view
    private final IGameLoop gameLoop;
    private final MainWindow window;
    private final GameCanvas gameCanvas;

    private KeyboardInputController keyboardInputController;
    private MouseInputController mouseInputController;

    public GameWindowController(Stage primaryStage) {
        window = new MainWindow(primaryStage, this);

        gameCanvas = window.getGameCanvas();
        // Create a new renderer using the graphics context supplied by the canvas.
        renderer = new Renderer(gameCanvas.getGraphicsContext2D());

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


    private void handleMenuLevelButton () {
        System.out.println("Level button clicked");
    }

    //TODO: to implement
    private void handleMenuScoreButton() {
        System.out.println("Score button clicked ");
    }

    public void handleMenuStartButton() {
        gameLoop.setPaused(false);
        window.hideMenu();
    }

    private void pauseGame() {
        gameLoop.setPaused(true);
        window.showMenu();
    }

    private void gameSetup() {
        game = new Game();

        // Initialize the keyboard input handler.
        keyboardInputController = new KeyboardInputController(window);
        // Initialize the mouse input handler
        mouseInputController = new MouseInputController(window);

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

    public MainWindow getWindow() {
        return window;
    }
}
