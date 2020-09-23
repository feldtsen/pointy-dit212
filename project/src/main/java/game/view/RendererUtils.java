package game.view;

import game.model.shape2d.ICircle;
import game.model.shape2d.IRectangle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RendererUtils {
    public static void clear(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(0, 0, 1200, 800);
    }

    public static void setBackgroundColor(GraphicsContext graphicsContext, Color color) {
        Canvas canvas = graphicsContext.getCanvas();
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getWidth());
    }

    public static void drawEntity(GraphicsContext graphicsContext, ICircle circle, Point2D position) {
        graphicsContext.fillOval(position.getX() - circle.getRadius(),
                position.getY() - circle.getRadius(),
                2*circle.getRadius(),
                2*circle.getRadius());
    }

    public static void drawEntity(GraphicsContext graphicsContext, IRectangle shape, Point2D position) {

    }

}
