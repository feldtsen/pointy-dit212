/*
 * Authors: Anton Hildingsson, Joachim Pedersen, Simon Genne, Mattias Oom, Erik Magnusson
 */

package game.controller;

import game.model.Game;
import game.controller.gameLoop.GameLoop;
import game.controller.gameLoop.IGameLoop;
import game.model.IGame;
import game.view.pages.MainWindow;
import game.view.pages.canvas.GameCanvas;
import game.view.renderer.Renderer;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

// Top level controller which initializes the model and the view and starts the game.
public class GameWindowController {
    // Game loop which is responsible for updating model
    private final IGameLoop gameLoop;

    // Entire model. This is updated by the game loop and runs the entire game
    private IGame game;

    // Renderer class for drawing to a graphics context. The renderer draws the actual gameplay
    // while other GUI elements, such as buttons and menus, are contained in the main window.
    private final Renderer  renderer;

    // Main window which creates a canvas with a graphics context, and handles menus and buttons
    private final MainWindow window;

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

                // Display the menu if the player wins the game
                if(game.isGameWin()) showMenu();

                // Displays game over message
                if(game.isGameOver()) handleGameOver();

                // Checks if all enemies have been defeated
                if (game.getCurrentLevel().getEnemies().isEmpty())  {
                    game.nextLevel();

                    // Register movement keys to new player object
                    registerPlayerControls();
                }

                // Set facing direction of player
                game.getCurrentLevel().getPlayer().setFacingTowards(mouseInputController.getMousePosition());

                // If all enemies are dead, progress to the next level
                if (game.getCurrentLevel().getEnemies().isEmpty()) game.nextLevel();
            }
        };

        // Start the game loop. At this point, the game is running.
        gameLoop.start();
    }

    // Updates the UI elements visible during gameplay
    private void updateUI() {
        // Update score panel
        window.getScorePanel().updateScore(game.getScore());

        // Update cooldown timers
        window.getAbilityBar().updateAbilities(game.getCurrentLevel().getPlayer().getAbilities());
    }

    private void handleGameOver() {
        // Clear all entities from screen.
        renderer.clearCanvas();

        // Show game over message.
        window.showGameOver();

        // Registers methods to new keys. Pressing P starts a new game, pressing ESC displays starting menu.
        keyboardInputController.registerAction(KeyCode.P,      this::restart);
        keyboardInputController.registerAction(KeyCode.ESCAPE, this::showMenu);
    }

    // Hide game over message and starts a new game
    private void restart() {
        window.hideGameOver();

        // Makes ability bar and score panel visible.
        window.showUI();

        // Setup new game
        gameSetup();
    }

    // Hides game over message and displays the starting menu.
    private void showMenu() {
        pauseGame();

        // Removes all entities from canvas.
        renderer.clearCanvas();

        // Removes game over message.
        window.hideGameOver();

        // Show menu.
        window.menuFadeOut();

        // Setup new game.
        gameSetup();
    }

    //TODO: implement
    public void handleMenuLevelButton() {
        System.out.println("Level button clicked");
    }
    public void handleMenuScoreButton() {
        System.out.println("Score button clicked ");
    }

    // Button action for starting the game
    public void handleMenuStartButton() {
        gameLoop.setPaused(false);
        window.menuFadeIn();
        window.getAbilityBar().setVisible(true);
        window.getScorePanel().setVisible(true);
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

        // Register player controls
        registerPlayerControls();
    }

    // Register player controls, i.e map keyboard and mouse inputs to certain player actions
    private void registerPlayerControls() {
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

        // Give key codes registered by the game pane a given action
        keyboardInputController.registerAction(KeyCode.ESCAPE, this::pauseGame);

        // Bind keys for player abilities
        keyboardInputController.registerAction(KeyCode.SHIFT, () -> game.activatePlayerAbility(0));
        keyboardInputController.registerAction(KeyCode.E, () -> game.activatePlayerAbility(1));

        // Bind mouse click to player ability
        mouseInputController.registerActionOnLeftClick(() -> game.activatePlayerAbility(2));

        // Bind mouse movement to updating the player facing position
        mouseInputController.registerActionOnMove(() -> game.getCurrentLevel().getPlayer().setFacingTowards(mouseInputController.getMousePosition()));
    }
}
