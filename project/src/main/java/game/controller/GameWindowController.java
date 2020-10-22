/*
 * Authors: Anton Hildingsson, Joachim Pedersen, Simon Genne, Mattias Oom, Erik Magnusson
 */

package game.controller;

import game.model.Game;
import game.controller.gameLoop.GameLoop;
import game.controller.gameLoop.IGameLoop;
import game.model.IGame;
import game.model.audio.AudioHandler;
import game.model.audio.IAudioHandler;
import game.model.score.IHighscoreHandler;
import game.model.score.HighscoreHandler;
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

    // Handles audio related actions
    private final IAudioHandler audioHandler = new AudioHandler();

    // Handles highscore
    private final IHighscoreHandler highscoreHandler = new HighscoreHandler();


    public GameWindowController() {
        highscoreHandler.writeToFile();

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
                keyboardInputController.applyHeldRegisteredActions();

                // Play sfx
                //audioHandler.playSoundEffects();

                // Update the game model with a global time step of 1 (normal speed)
                game.update(delta, 1);

                // Shows winning message
                if(game.isGameWin()) handleGameState("YOU WIN", "You da man (or woman). Press ESC to return to menu.");

                // Shows game over message
                if(game.isGameOver()) handleGameState("GAME OVER", "Press R to play again or ESC to return to menu.");

                // Checks if all enemies have been defeated
                if (game.getCurrentLevel().getEnemies().isEmpty())  {
                    // TODO: set timer score to score table if it's a new high score
                    checkHighscore(game.getTime());
                    game.nextLevel();
                    if (!game.isGameWin()) {
                        handleGameState("LEVEL COMPLETE", "\nScore: " + game.getTime() + "\n\nPress R to continue.");
                    }
                }

                // Set facing direction of player
                game.getCurrentLevel().getPlayer().setFacingTowards(mouseInputController.getMousePosition());
            }
        };

        // Start the game loop. At this point, the game is running.
        gameLoop.start();
    }

    // Updates the UI elements visible during gameplay
    private void updateUI() {
        // Update score panel
        window.getScorePanel().updateScore(game.getTime());

        // Update cooldown timers
        window.getAbilityBar().updateAbilities(game.getCurrentLevel().getPlayer().getAbilities());
    }


    private void handleGameState(String gameStateMessage, String gameStateInstructions) {
        // Reset the timer
        game.resetTimer();

        // Stop the game
        gameLoop.setPaused(true);

        // Clear all entities from screen.
        renderer.clearCanvas();
        // Set message
        window.setGameState( gameStateMessage, gameStateInstructions);
        // Show game over message.
        window.showGameState();

        window.hideUI();
    }

    // Hide game over message and starts a new game
    private void restart() {
        gameLoop.setPaused(false);

        window.hideGameState();

        // Makes ability bar and score panel visible.
        window.showUI();

        // Setup new game
        game.restartLevel();

        // Register player controls to new player object.
        registerPlayerControls();
    }

    // Hides game over message and displays the starting menu.
    private void pauseAndReload() {
        pause();
        // Removes game over message.
        window.hideGameState();
        // Setup new game.
        gameSetup();
    }

    // Checks if new highscore
    private void checkHighscore (double time) {
        String currentLevel = String.format("%d", game.getCurrentLevel().getLevelNr());

        // If the time for completing the current level is lesser than the stored time, overwrite it
        if (time < highscoreHandler.getHighscore(currentLevel)) {
         highscoreHandler.setHighscore(currentLevel, time);
        }
    }

    //TODO: implement
    public void handleMenuLevelButton() {
        System.out.println("Level button clicked");
    }
    public void handleMenuScoreButton() {
        System.out.println("Score button clicked ");
    }

    // Button action for starting the game
    public void unpause() {
        gameLoop.setPaused(false);
        window.hideMenu();
        window.showUI();
    }

    // Action for pausing the game
    private void pause() {
        gameLoop.setPaused(true);
        window.showMenu();
        window.hideUI();
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

        // Registers methods to new keys. Pressing P starts a new game, pressing ESC displays starting menu.
        keyboardInputController.registerPressedAction(KeyCode.R,      ()->{
            if(gameLoop.isPaused()) restart();
        });
        keyboardInputController.registerPressedAction(KeyCode.ESCAPE, () -> {
            if (game.isGameOver() || game.isGameWin()) pauseAndReload();
            else if (gameLoop.isPaused()) unpause();
            else if (!gameLoop.isPaused()) pause();
        });

        keyboardInputController.registerPressedAction(KeyCode.M, audioHandler::toggleMusic);

        // Bind keys to player movement
        keyboardInputController.registerHeldAction(KeyCode.W, game.getCurrentLevel().getPlayer()::moveUp);
        keyboardInputController.registerHeldAction(KeyCode.A, game.getCurrentLevel().getPlayer()::moveLeft);
        keyboardInputController.registerHeldAction(KeyCode.S, game.getCurrentLevel().getPlayer()::moveDown);
        keyboardInputController.registerHeldAction(KeyCode.D, game.getCurrentLevel().getPlayer()::moveRight);


        // Bind keys for player abilities
        keyboardInputController.registerHeldAction(KeyCode.SHIFT, () -> {
            // If the ability successfully activates, play the corresponding sound
            if(game.activatePlayerAbility(0)) audioHandler.registerSoundEffect("dash");
        });
        keyboardInputController.registerHeldAction(KeyCode.E,     () -> {
            // If the ability successfully activates, play the corresponding sound
            if (game.activatePlayerAbility(1)) audioHandler.registerSoundEffect("shockwave");
        });

        // Bind mouse click to player ability
        mouseInputController.registerActionOnLeftClick(() -> {
            // If the ability successfully activates, play the corresponding sound
            if (game.activatePlayerAbility(2)) audioHandler.registerSoundEffect("reflect");
        });

        // Bind mouse movement to updating the player facing position
        mouseInputController.registerActionOnMove(() -> game.getCurrentLevel().getPlayer().setFacingTowards(mouseInputController.getMousePosition()));
    }
}
