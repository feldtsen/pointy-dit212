package game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import game.model.gameLoop.GameLoop;
import game.model.gameLoop.IGameLoop;
import game.model.behavior.movement.SeekingBehaviour;
import game.model.entity.enemy.Enemy;
import game.model.player.Player;
import game.model.shape2d.ICircle;
import game.model.shape2d.Shapes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
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

    private static class CurrentDirection {
        private final ArrayList<Boolean> directions = new ArrayList<>();

        public CurrentDirection() {
            directions.add(false);
            directions.add(false);
            directions.add(false);
            directions.add(false);
        }

        public void register(KeyEvent e) {
            switch (e.getCode()) {
                case W:
                    directions.set(0, true);
                    break;
                case A:
                    directions.set(1, true);
                    break;
                case S:
                    directions.set(2, true);
                    break;
                case D:
                    directions.set(3, true);
                    break;

            }

        }

        public void unregister(KeyEvent e){
            switch (e.getCode()) {
                case W:
                    directions.set(0, false);
                    break;
                case A:
                    directions.set(1, false);
                    break;
                case S:
                    directions.set(2, false);
                    break;
                case D:
                    directions.set(3, false);
                    break;
            }


        }

        public ArrayList<Boolean> getCurrentDirections(){
            return directions;
        }

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
    private void handleMenuStartButton(ActionEvent e) {
        Button menuStartButton = (Button) e.getSource();
        HBox menuContainer = (HBox) menuStartButton.getParent();
        menuContainer.toBack();

        menuContainer.getScene().lookup("#menuPauseButton").toFront();

        gameLoop.setPaused(false);

    }


    @FXML
    private void handleReturnToMenuButton(ActionEvent e) {
        Button returnToMenuButton = (Button) e.getSource();
        returnToMenuButton.toBack();
        returnToMenuButton.getScene().lookup("#menuContainer").toFront();
        gameLoop.setPaused(true);
    }

    private void startGame() throws IOException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Player player = new Player(new Point2D(575, 375), 30, 2500, 1000);
        player.setFriction(3);
        Enemy enemy = new Enemy(new Point2D(100,100), 50, 1000, 1000, 1,null, new SeekingBehaviour(), player);
        enemy.setFriction(3);
        CurrentDirection currentDirection = new CurrentDirection();

        gamePane.setOnKeyPressed(currentDirection::register);
        gamePane.setOnKeyReleased(currentDirection::unregister);


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

                if(currentDirection.getCurrentDirections().get(0)) {
                    player.moveUp();
                }
                if(currentDirection.getCurrentDirections().get(1)) {
                    player.moveLeft();
                }
                if(currentDirection.getCurrentDirections().get(2)) {
                    player.moveDown();
                }

                if(currentDirection.getCurrentDirections().get(3)) {
                    player.moveRight();
                }

                player.update(delta);
                enemy.update(delta);
            }
        };
        gameLoop.start();
    }
}
