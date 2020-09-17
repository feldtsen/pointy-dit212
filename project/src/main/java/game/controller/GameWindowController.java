package game.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import game.GameLoop;
import game.model.entity.IEntity;
import game.model.entity.movable.MovableEntity;
import game.model.player.Player;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import game.App;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.crypto.Cipher;

public class GameWindowController {

    private class DummyPlayer extends MovableEntity {
        public DummyPlayer(){
            super(new Point2D(10, 10), 50, 5, 5);
        }

        public void moveRight (){
            this.position = new Point2D(this.position.getX() + 5, this.position.getY());
        }
        public void moveLeft (){
            this.position = new Point2D(this.position.getX() - 5, this.position.getY());
        }
        public void moveDown (){
            this.position = new Point2D(this.position.getX(), this.position.getY() + 5);
        }
        public void moveUp (){
            this.position = new Point2D(this.position.getX(), this.position.getY() - 5);
        }

    }

    @FXML
    private Canvas canvas;

    private class CurrentDirection {
        private ArrayList<Boolean> directions = new ArrayList<>();

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
        public boolean keyPressed(){
            return directions.get(0) || directions.get(1) || directions.get(2) || directions.get(3);
        }

        public ArrayList<Boolean> getCurrentDirections(){
            return directions;
        }

    }
    @FXML
    Pane gamePane;

    @FXML
    private void startGame() throws IOException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Player player = new Player(new Point2D(575, 375), 50, 2500, 1000);
        CurrentDirection currentDirection = new CurrentDirection();

        gamePane.setOnKeyPressed(currentDirection::register);
        gamePane.setOnKeyReleased(currentDirection::unregister);

        Button start = (Button) gamePane.lookup("#startGameButton");
        start.setText("Wow");
        start.toBack();

        new GameLoop(1000)
        {
            public void update(double delta)
            {
                gc.clearRect(0, 0, 1200, 800);
                gc.setFill(Color.rgb(50, 50, 50));
                gc.fillRect(0, 0, 1200, 800);
                gc.setFill(Color.WHITE);
                gc.fillOval(player.getPosition().getX(), player.getPosition().getY(), player.getWidth(), player.getHeight());

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

                player.setVelocity(player.getVelocity().multiply(0.98));
                player.update(delta );
            }
        }.start();
    }
}
