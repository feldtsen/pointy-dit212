package game.view.pages.canvas;

import javafx.scene.canvas.Canvas;

public class GameCanvas extends Canvas {
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
