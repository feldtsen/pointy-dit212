package game.view;

import game.model.shape2d.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

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

    // TODO: refactor this into rectangle
    public static void translate(GraphicsContext graphicsContext, IRectangle rectangle) {
        double rotationCenterX = (rectangle.getWidth()) / 2;
        double rotationCenterY = (rectangle.getHeight()) / 2;

        graphicsContext.save();
        graphicsContext.translate(rotationCenterX + 400, rotationCenterY + 400);
        graphicsContext.rotate(rectangle.getRotation());
        graphicsContext.translate(-rotationCenterX - 400, -rotationCenterY - 400);

        graphicsContext.fillRect(400, 400, rectangle.getWidth(), rectangle.getHeight());
        graphicsContext.restore();

    }

}
