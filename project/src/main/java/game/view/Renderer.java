package game.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer implements IRenderer{
    private final GraphicsContext graphicsContext;
    private final double WIDTH;
    private final double HEIGHT;

    public Renderer (GraphicsContext graphicsContext) {
       this.graphicsContext = graphicsContext;
       WIDTH = graphicsContext.getCanvas().getWidth();
       HEIGHT = graphicsContext.getCanvas().getHeight();
    }


    @Override
    public void clear() {
        graphicsContext.clearRect(0, 0, 1200, 800);
    }

    @Override
    public void setBackgroundColor(Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
