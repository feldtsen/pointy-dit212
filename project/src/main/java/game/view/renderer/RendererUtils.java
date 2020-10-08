package game.view.renderer;

import game.model.shape2d.*;
import game.util.Utils;
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
        graphicsContext.fillRect(
                0,
                0,
                graphicsContext.getCanvas().getWidth(),
                graphicsContext.getCanvas().getHeight()
        );

    }


    // Draws a circle to the screen
    public static void drawCircle(GraphicsContext graphicsContext, Color color, ICircle circle, Point2D position) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(
                position.getX() - circle.getRadius(),
                position.getY() - circle.getRadius(),
                2*circle.getRadius(),
                2*circle.getRadius()
        );
    }
    // Draws a ring to the screen
    public static void drawRing(GraphicsContext graphicsContext, Color color, ICircle circle, Point2D position) {
        graphicsContext.setStroke(color);
        graphicsContext.strokeOval(
                position.getX() - circle.getRadius(),
                position.getY() - circle.getRadius(),
                2*circle.getRadius(),
                2*circle.getRadius()
        );
    }

    // Draws a rectangle to the screen
    public static void drawRectangle(GraphicsContext graphicsContext, Color color, IRectangle shape, Point2D position) {
        SaveAndTranslate(graphicsContext, shape, position);

        graphicsContext.setFill(color);
        graphicsContext.fillRect(position.getX(), position.getY(), shape.getWidth(), shape.getHeight());

        graphicsContext.restore();
    }


    public static void drawTriangle(GraphicsContext graphicsContext, Color color, ITriangle shape, Point2D position) {
        SaveAndTranslate(graphicsContext, shape, position);

        graphicsContext.setFill(color);
        graphicsContext.fillPolygon(
                new double[]{position.getX(), position.getX() + shape.getWidth(), position.getX() + shape.getWidth()/2},
                new double[]{position.getY(), position.getY()                   , position.getY() + shape.getHeight()},
                3);


        graphicsContext.restore();
    }

    // Draws a line from a start to stop
    public static void drawLine(GraphicsContext graphicsContext, Color color, Point2D start, Point2D stop, double width) {
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(width);
        graphicsContext.strokeLine(start.getX(), start.getY(), stop.getX(), stop.getY());
    }

    private static void SaveAndTranslate(GraphicsContext graphicsContext, IShape2D shape, Point2D position) {
        // Saves  attributes, such as paint data and transform data
        graphicsContext.save();

        // Translates the shape to a position
        graphicsContext.translate(position.getX(), position.getY());

        // Rotates the current transform
        graphicsContext.rotate(Utils.radianToDegrees(shape.getRotation()) - 90);

        // Translate to where you want it
        graphicsContext.translate(-position.getX() - shape.getWidth()/2, -position.getY() - shape.getHeight()/2);
    }

}
