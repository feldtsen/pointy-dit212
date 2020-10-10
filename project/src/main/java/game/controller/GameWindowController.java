package game.controller;

import game.model.Game;
import game.controller.gameLoop.GameLoop;
import game.controller.gameLoop.IGameLoop;
import game.model.IGame;
import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.view.pages.MainWindow;
import game.view.pages.canvas.GameCanvas;
import game.view.renderer.Renderer;
import game.view.pages.score.IScorePanel;
import game.view.renderer.RendererUtils;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import java.io.FileNotFoundException;

public class GameWindowController {
    private IGame game;               // Model
    private final Renderer  renderer; // view
    private final MainWindow window;  // view
    private final IGameLoop gameLoop;

    private KeyboardInputController keyboardInputController;
    private MouseInputController mouseInputController;

    private IScorePanel scorePanel;

    public GameWindowController() {
        // Init. view component
        window = new MainWindow(this);

        // Listen for window resize
        window.widthProperty().addListener(e -> this.resize());

        GameCanvas gameCanvas = window.getGameCanvas();
        // Create a new renderer using the graphics context supplied by the canvas.
        renderer = new Renderer(gameCanvas.getGraphicsContext2D());

        // Set scorePanel to instance created by window.
        scorePanel = window.getScorePanel();

        // Initialize the game and map all the keys to their corresponding actions.
        gameSetup();

        // Create a game loop. The update method will be called every frame.
        // Game loop is initialized with a improbably high desired fps value, to ensure the
        // game is run at max fps possible.
        gameLoop = new GameLoop(1000) {
            @Override
            public void update(double delta) {
                // TODO: do it elsewhere?
                resize();

                // Render the current level
                renderer.drawEntities(game.getCurrentLevel());
                // Render ability effects
                renderer.drawAbilities(game.getActiveAbilityActions(), game.getActiveAbilityTimes());

                // Update score panel
                scorePanel.updateScore(game.getCurrentLevel().getPlayer(), game.getScore());

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

    private void resize() {
        //TODO what todo here
        ILevel currentLevel = game.getCurrentLevel();
        IPlayer player = currentLevel.getPlayer();
        double scalingRatio = window.getWidth() / 1000;



        //player.getShape().resize(scalingRatio);

        for (IEnemy enemy : currentLevel.getEnemies()) {
            //enemy.getShape().resize(scalingRatio);
        }

        for (IProjectile projectile : currentLevel.getProjectiles()) {
            //projectile.getShape().resize();
        }

        for (IObstacle obstacle : currentLevel.getObstacles()) {
            // obstacle.getShape().resize();

        }
    }


    public void handleMenuLevelButton() {
        System.out.println("Level button clicked");
    }

    //TODO: to implement
    public void handleMenuScoreButton() {
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

    public MainWindow getWindow() {
        return window;
    }

    public void handleMenuExitButton() {
        Platform.exit();
    }

    private void gameSetup() {
        try {
            game = new Game();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


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

        keyboardInputController.registerAction(KeyCode.SHIFT, () -> game.activatePlayerAbility(0));
        keyboardInputController.registerAction(KeyCode.E, () -> game.activatePlayerAbility(1));

        // Register for mouse events
        mouseInputController.registerActionOnLeftClick(() -> game.activatePlayerAbility(2));
        mouseInputController.registerActionOnMove(     () -> game.setPlayerFacingPosition(mouseInputController.getMousePosition()));

    }


}
