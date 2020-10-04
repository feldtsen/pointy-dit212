package game.view.pages.canvas;

import game.view.renderer.Renderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    Renderer renderer = new Renderer(this.getGraphicsContext2D());

    public GameCanvas () {
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }
    private void draw() {
        double width = this.getWidth();
        double height = this.getHeight();

        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, width, height);

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(0, 0, width, height);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return this.getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return this.getHeight();
    }
}
