package game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import game.model.Game;
import game.model.gameLoop.GameLoop;
import game.model.gameLoop.IGameLoop;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import game.view.Renderer;
import game.view.RendererUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameWindowController implements Initializable {

    @FXML
    private StackPane gamePane;

    @FXML
    private Canvas canvas;

    private IGameLoop gameLoop;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(30, 30, 30));
        gc.fillRect(0, 0, 1200, 800);
        try {
            startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void startGame() throws IOException {
        Game game = new Game();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Renderer renderer = new Renderer(graphicsContext);

        UserInputController.init(gamePane);
        UserInputController.registerAction(KeyCode.W, game.getCurrentLevel().getPlayer()::moveUp);
        UserInputController.registerAction(KeyCode.A, game.getCurrentLevel().getPlayer()::moveLeft);
        UserInputController.registerAction(KeyCode.S, game.getCurrentLevel().getPlayer()::moveDown);
        UserInputController.registerAction(KeyCode.D, game.getCurrentLevel().getPlayer()::moveRight);
        UserInputController.registerAction(KeyCode.ESCAPE, this::pauseGame);

        gameLoop = new GameLoop(1000) {
            @Override
            public void update(double delta) {
                renderer.draw(game.getCurrentLevel());

                UserInputController.update();
                game.update(delta, 1);
            }
        };
        gameLoop.start();
    }
}
