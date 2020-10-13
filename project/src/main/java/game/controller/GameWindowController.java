package game.controller;

import game.model.Game;
import game.controller.gameLoop.GameLoop;
import game.controller.gameLoop.IGameLoop;
import game.model.IGame;
import game.view.pages.MainWindow;
import game.view.pages.canvas.GameCanvas;
import game.view.renderer.Renderer;
import game.view.pages.score.IScorePanel;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import java.io.FileNotFoundException;

// Top level controller which initializes the model and the view and
// starts the game.
public class GameWindowController {
    private final IGameLoop gameLoop;
    private IGame game;               // Model
    private final Renderer  renderer; // view
    private final MainWindow window;  // view

    // Handles keyboard input from the user
    private KeyboardInputController keyboardInputController;

    // Handles mouse input from the user
    private MouseInputController mouseInputController;

    public GameWindowController() {
        // Initializes main window view component
        window = new MainWindow(this);

        // Fetches the canvas which will be used to render the game
        GameCanvas gameCanvas = window.getGameCanvas();

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
                renderer.drawEntities(game.getCurrentLevel());
                // Render ability effects
                renderer.drawAbilities();

                // Updated the UI with relevant information (like cooldown time and game score)
                updateUI();

                // Apply all registered keyboard actions
                keyboardInputController.applyRegisteredActions();

                // Update the game model with a global time step of 1 (normal speed)
                game.update(delta, 1);

                // Reinitialize game on player death
                // TODO: handle player death properly
                if(game.isGameOver()) gameSetup();

                // If all enemies are dead, progress to the next level
                // TODO: should be performed by game itself!?!?!?
                if (game.getCurrentLevel().getEnemies().isEmpty()) game.nextLevel();
            }
        };

        // Start the game loop. At this point, the game is running.
        gameLoop.start();
    }

    private void updateUI() {
        // Update score panel
        window.getScorePanel().updateScore(game.getCurrentLevel().getPlayer(), game.getScore());

        // Update cooldown timers
        window.getAbilityBar().updateAbilities(game.getCurrentLevel().getPlayer().getAbilities());
    }

    //TODO: implement
    public void handleMenuLevelButton() {
        System.out.println("Level button clicked");
    }

    //TODO: implement
    public void handleMenuScoreButton() {
        System.out.println("Score button clicked ");
    }

    // Button action for starting the game
    public void handleMenuStartButton() {
        gameLoop.setPaused(false);
        window.menuFadeIn();
    }

    // Action for pausing the game
    private void pauseGame() {
        gameLoop.setPaused(true);
        window.menuFadeOut();
    }

    public MainWindow getWindow() {
        return window;
    }

    public void handleMenuExitButton() {
        Platform.exit();
    }

    private void gameSetup() {
        // Clears all the currently active abilities. This ensures abilities are not continued to be drawn after
        // a restart.
        renderer.clearAbilities();

        // Create game
        game = new Game();

        // Make sure view listens for ability action events
        // This ensures the view knows when to draw certain effects, and gives the view itself the ability
        // to control for how long an effect will be seen on screen.
        game.registerListener(renderer);

        // Initialize the keyboard input handler.
        keyboardInputController = new KeyboardInputController(window);
        // Initialize the mouse input handler
        mouseInputController = new MouseInputController(window);

        // Bind key for pausing the game
        keyboardInputController.registerAction(KeyCode.ESCAPE, this::pauseGame);

        // Bind keys to player movement
        keyboardInputController.registerAction(KeyCode.W, game.getCurrentLevel().getPlayer()::moveUp);
        keyboardInputController.registerAction(KeyCode.A, game.getCurrentLevel().getPlayer()::moveLeft);
        keyboardInputController.registerAction(KeyCode.S, game.getCurrentLevel().getPlayer()::moveDown);
        keyboardInputController.registerAction(KeyCode.D, game.getCurrentLevel().getPlayer()::moveRight);

        // Bind keys for player abilities
        keyboardInputController.registerAction(KeyCode.SHIFT, () -> game.activatePlayerAbility(0));
        keyboardInputController.registerAction(KeyCode.E, () -> game.activatePlayerAbility(1));

        // Bind mouse click to player ability
        mouseInputController.registerActionOnLeftClick(() -> game.activatePlayerAbility(2));

        // Bind mouse movement to updating the player facing position
        mouseInputController.registerActionOnMove(     () -> game.setPlayerFacingPosition(mouseInputController.getMousePosition()));
    }
}
