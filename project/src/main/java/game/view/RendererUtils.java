package game.view;

import game.model.shape2d.ICircle;
import game.model.shape2d.IRectangle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RendererUtils {
    private final GraphicsContext graphicsContext;
    private final double WIDTH;
    private final double HEIGHT;

    public RendererUtils(GraphicsContext graphicsContext) {
       this.graphicsContext = graphicsContext;
       WIDTH = graphicsContext.getCanvas().getWidth();
       HEIGHT = graphicsContext.getCanvas().getHeight();
    }

    public void clear() {
        graphicsContext.clearRect(0, 0, 1200, 800);
    }

    public void setBackgroundColor(Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void drawEntity(ICircle circle, Point2D position) {
        graphicsContext.fillOval(position.getX() - circle.getRadius(),
                position.getY() - circle.getRadius(),
                2*circle.getRadius(),
                2*circle.getRadius());
    }

    public void drawEntity(IRectangle shape, Point2D position) {

    }

}
