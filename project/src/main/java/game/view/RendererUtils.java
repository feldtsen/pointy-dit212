package game.view;

import game.model.shape2d.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RendererUtils {
    // Method for clearing the screen
    public static void clear(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(
                0,
                0,
                graphicsContext.getCanvas().getWidth(),
                graphicsContext.getCanvas().getHeight()
        );
    }

    // Method for setting a background color
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


    // Draws a particular shape (TODO: so far, just a circle) to the screen
    public static void drawShape(GraphicsContext graphicsContext, Color color, ICircle circle, Point2D position) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(
                position.getX() - circle.getRadius(),
                position.getY() - circle.getRadius(),
                2*circle.getRadius(),
                2*circle.getRadius()
        );
    }

    // Draws a rotatable shape to the screen
    public static void drawShape(GraphicsContext graphicsContext, Color color, IShape2D shape, Point2D position) {
        double rotationCenterX = shape.getWidth() / 2;
        double rotationCenterY = shape.getHeight() / 2;

        graphicsContext.save();

        graphicsContext.translate(position.getX(), position.getY() );
        graphicsContext.rotate(shape.getRotation());
        graphicsContext.translate(-position.getX() - rotationCenterX, -position.getY() - rotationCenterY);

        graphicsContext.setFill(color);
        graphicsContext.fillRect(position.getX(), position.getY(), shape.getWidth(), shape.getHeight());

        graphicsContext.restore();


    }

    // Draws a line from a start to stop
    public static void drawLine(GraphicsContext graphicsContext, Color color, Point2D start, Point2D stop, double width) {
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(width);
        graphicsContext.strokeLine(start.getX(), start.getY(), stop.getX(), stop.getY());
    }
}
