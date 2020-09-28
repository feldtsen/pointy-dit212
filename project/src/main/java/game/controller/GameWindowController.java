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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class GameWindowController implements Initializable {

    private IGameLoop gameLoop;
    private IGame game;
    private Renderer renderer;

    @FXML
    private StackPane gamePane;

    @FXML
    private Canvas canvas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        renderer = new Renderer(canvas.getGraphicsContext2D());
        gameSetup();

        // Gets called every frame
        gameLoop = new GameLoop(1000) {
            @Override
            public void update(double delta) {
                renderer.draw(game.getCurrentLevel());

                KeyboardInputHandler.applyRegisteredActions();

                game.update(delta, 1);

                if(game.isGameOver()) gameSetup();
            }
        };

        gameLoop.start();
    }

    @FXML
    private void handleMenuLevelButton () {
        System.out.println("Level button clicked");
    }

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
        // Give key codes registered by the game pane a given action
        KeyboardInputHandler.init(gamePane);
        KeyboardInputHandler.registerAction(KeyCode.W, game.getCurrentLevel().getPlayer()::moveUp);
        KeyboardInputHandler.registerAction(KeyCode.A, game.getCurrentLevel().getPlayer()::moveLeft);
        KeyboardInputHandler.registerAction(KeyCode.S, game.getCurrentLevel().getPlayer()::moveDown);
        KeyboardInputHandler.registerAction(KeyCode.D, game.getCurrentLevel().getPlayer()::moveRight);
        KeyboardInputHandler.registerAction(KeyCode.ESCAPE, this::pauseGame);
    }
}
