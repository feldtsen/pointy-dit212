package game.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import game.App;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameWindowController {

    @FXML
    private Canvas canvas;

    @FXML
    private void switchToSecondary() throws IOException {
        //App.setRoot("secondary");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(100, 100, 100, 100);
    }
}
