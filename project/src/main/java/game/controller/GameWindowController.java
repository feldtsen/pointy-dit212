/*
 * Authors: Anton Hildingsson, Joachim Pedersen, Simon Genne, Mattias Oom, Erik Magnusson
 *
 * Top level controller which initializes the model and the view and starts the game.
 * The game window controller creates a model instance, a game loop, a renderer and a main window, as
 * well as different input controllers, and ties all these components together.
 */

package game.controller;

import game.model.Game;
import game.controller.gameLoop.GameLoop;
import game.controller.gameLoop.IGameLoop;
import game.model.IGame;
import game.model.audio.AudioHandler;
import game.model.audio.IAudioHandler;
import game.model.entity.player.IPlayer;
import game.model.score.IHighscoreHandler;
import game.model.score.HighscoreHandler;
import game.view.pages.MainWindow;
import game.view.pages.canvas.GameCanvas;
import game.view.renderer.Renderer;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

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

                // Shows game over message
                if (game.isGameOver()) {
                    handleGameState("GAME OVER", "\nPress R to play again or ESC to return to the menu.");
                    return;
                }  // Shows winning message
                else if (game.isGameWin()) {
                    handleGameState("YOU WIN", "\nPress ESC to return to the menu.");
                    return;
                }
                // Checks if all enemies have been defeated
                else if (game.isNextLevel())  {
                    // Check if the newly acquired time is better than previously stored
                    checkHighscore(game.getTime());


                    handleGameState("LEVEL COMPLETE", "\nTime " + String.format("%.1f", game.getTime()) + "s. Press R to continue or ESC to return to the menu.");
                    return;

                }


                if (game.getCurrentLevel() == null || game.getCurrentLevel().getPlayer() == null) return;
                // Set facing direction of player
                updatePlayerFacingDirection();

                // Render the current level
                renderer.drawEntities(game.getCurrentLevel());

                // Render ability effects
                renderer.drawAbilities();

                // Updated the UI with relevant information (like cooldown time and game score)
                updateUI();

                // Apply all registered keyboard actions
                keyboardInputController.applyHeldRegisteredActions();

                // Play sfx
                audioHandler.playSoundEffects();

                // Update the game model with a global time step of 1 (normal speed)
                game.update(delta, 1);

            }
        };

        // Start the game loop. At this point, the game is running.
        gameLoop.start();
    }

    private void continueGame()  {
        // Loads the next level
        game.nextLevel();

        gameLoop.setPaused(false);
        window.hideUI();

        restart();
    }

    private void updatePlayerFacingDirection() {
        IPlayer player = game.getCurrentLevel().getPlayer();
        Point2D mousePosition = mouseInputController.getMousePosition();

        player.setFacingTowards(mousePosition, window.getWidth(), window.getHeight());
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
        window.setGameState(gameStateMessage, gameStateInstructions);

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
        if (game.getCurrentLevel() == null || game.getCurrentLevel().getPlayer() == null) return;
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
        String currentLevel = String.format("%d", game.getCurrentLevel().getLevelNumber());

        // If the time for completing the current level is lesser than the stored time, overwrite it
        if (time < highscoreHandler.getHighscore(currentLevel)) {
             highscoreHandler.setHighscore(currentLevel, time);
        }
    }

    public void handleMenuLevelButton() {
        window.clearLevelPanel();

        // If we have obtained a highscore for the level, make it possible to load that level from the level pane
        highscoreHandler.getStoredHighscores().forEach((level, time) ->  {
            Button load = new Button("Load level " + level);
            load.setOnMouseClicked(e -> retrieveLevel(Integer.parseInt(level)));
            window.addLevelButton(load);
        });


        int nextLevelNr = highscoreHandler.getStoredHighscores().size() + 1;
        // Check if level exist before adding it to the level panel
        if (game.levelExist(nextLevelNr)) {
            Button nextLevel = new Button("Load level " + nextLevelNr);
            nextLevel.setOnMouseClicked(e -> retrieveLevel(nextLevelNr));
            window.addLevelButton(nextLevel);
        }

        // Display the panel where you can select the level
        window.showLevelPanel();
    }

    private void retrieveLevel(int level) {
        window.hideMenu();
        game.changeLevel(level);
        restart();
    }

    public void handleMenuScoreButton() {
        window.clearHighscorePanel();
        highscoreHandler.getStoredHighscores().forEach(window::addHighscore);
        window.showHighscores();
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
            if (game.isNextLevel()) continueGame();
            else if(game.isGameOver()) restart();
        });

        keyboardInputController.registerPressedAction(KeyCode.ESCAPE, () -> {
            if (game.isNextLevel()) {
                continueGame();
                pause();
            }
            else if (game.isGameOver() || game.isGameWin()) pauseAndReload();
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

    }
}
