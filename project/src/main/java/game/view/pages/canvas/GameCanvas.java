package game.view.pages.canvas;

import javafx.scene.canvas.Canvas;

public class GameCanvas extends Canvas {
    public GameCanvas () {
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    private void draw() {
        /*
        double width = this.getWidth();
        double height = this.getHeight();

        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, width, height);

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(0, 0, width, height);

         */
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
