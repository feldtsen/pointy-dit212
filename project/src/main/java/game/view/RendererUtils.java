package game.view;

import game.model.shape2d.ICircle;
import game.model.shape2d.IRectangle;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RendererUtils {
    public static void clear(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(
                0,
                0,
                graphicsContext.getCanvas().getWidth(),
                graphicsContext.getCanvas().getHeight()
        );
    }

    public static void setBackgroundColor(GraphicsContext graphicsContext, Color color) {
        graphicsContext.setFill(color);

        //TODO: should we repeat fillRect instead? Now we heap allocate a new rectangle every frame.
        RendererUtils.drawShape(
                graphicsContext,
                color,
                new Rectangle(graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight(), 0),
                new Point2D(0, 0)
        );
    }


    public static void drawShape(GraphicsContext graphicsContext, Color color, ICircle circle, Point2D position) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(
                position.getX() - circle.getRadius(),
                position.getY() - circle.getRadius(),
                2*circle.getRadius(),
                2*circle.getRadius()
        );
    }

    public static void drawShape(GraphicsContext graphicsContext, Color color, IRectangle rectangle, Point2D position) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(
                position.getX(),
                position.getY(),
                rectangle.getWidth(),
                rectangle.getHeight()
        );
    }

}
