package game.controller;

import java.net.URL;
import java.util.ResourceBundle;

import game.model.Game;
import game.model.gameLoop.GameLoop;
import game.model.gameLoop.IGameLoop;
import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import game.model.shape2d.Shapes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
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
        startGame();
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
        System.out.println("start");
        gameLoop.setPaused(false);
        gamePane.lookup("#menuContainer").toBack();
        ((Button) gamePane.lookup("#menuStartButton")).setText("RETURN");
    }

    @FXML
    private void pauseGame() {
        gameLoop.setPaused(true);
        gamePane.lookup("#menuContainer").toFront();
    }

    private void startGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Player player = new Player(new Point2D(575, 375), 30, 2500, 1000);
        player.setFriction(3);
        Enemy enemy = new Enemy(new Point2D(100,100), 50, 1000, 1000, 1,null, new SeekingBehaviour(), player);
        enemy.setFriction(3);

        UserInputController.init(gamePane);
        UserInputController.registerAction(KeyCode.W, player::moveUp);
        UserInputController.registerAction(KeyCode.A, player::moveLeft);
        UserInputController.registerAction(KeyCode.S, player::moveDown);
        UserInputController.registerAction(KeyCode.D, player::moveRight);
        UserInputController.registerAction(KeyCode.ESCAPE, this::pauseGame);

        gameLoop = new GameLoop(1000) {
            @Override
            public void update(double delta) {
                gc.clearRect(0, 0, 1200, 800);
                gc.setFill(Color.rgb(30, 30, 30));
                gc.fillRect(0, 0, 1200, 800);
                boolean collision = Shapes.testCollision(player.getShape(), player.getPosition(), enemy.getShape(), enemy.getPosition());
                if(collision) {
                    gc.setFill(Color.RED);
                } else {
                    gc.setFill(Color.WHITE);
                }
                gc.fillOval(player.getPosition().getX() - player.getShape().getRadius(),
                        player.getPosition().getY() - player.getShape().getRadius(),
                        2*player.getShape().getRadius(),
                        2*player.getShape().getRadius());
                gc.fillOval(enemy.getPosition().getX() - enemy.getShape().getRadius(),
                        enemy.getPosition().getY() - enemy.getShape().getRadius(),
                        2*enemy.getShape().getRadius(),
                        2*enemy.getShape().getRadius());

                UserInputController.update();
                player.update(delta);
                enemy.update(delta);
                Game.containToBounds(player);
                Game.containToBounds(enemy);
            }
        };
        gameLoop.start();
    }
}
