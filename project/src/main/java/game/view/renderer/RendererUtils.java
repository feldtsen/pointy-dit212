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


    // Draws a particular shape (TODO: so far, just a circle) to the screen
    public static void drawShape(GraphicsContext graphicsContext, Color color, ICircle circle, Point2D position) {
        //graphicsContext.setFill(color);
        //TODO fixed color for testing
        graphicsContext.setFill(Color.SEAGREEN);
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
        graphicsContext.rotate(Utils.radianToDegrees(shape.getRotation()));
        graphicsContext.translate(-position.getX() - rotationCenterX, -position.getY() - rotationCenterY);

        graphicsContext.setFill(color);
        graphicsContext.fillRect(position.getX(), position.getY(), shape.getWidth(), shape.getHeight());

        graphicsContext.restore();


    }
    public static void drawShape(GraphicsContext graphicsContext, Color color, Triangle shape, Point2D position) {
        double rotationCenterX = shape.getWidth() / 2;
        double rotationCenterY = shape.getHeight() / 2;



        graphicsContext.save();

        graphicsContext.translate(position.getX(), position.getY() );
        graphicsContext.rotate(Utils.radianToDegrees(shape.getRotation()));
        graphicsContext.translate(-position.getX() - rotationCenterX, -position.getY() - rotationCenterY );

        graphicsContext.setFill(Color.DARKGREEN);
        /*
        graphicsContext.fillPolygon(
                new double[]{100, 100 + shape.getWidth(), 100 + (shape.getWidth()/2)},
                new double[]{100, 100,                    100 - shape.getHeight()},
                3);
         */

        graphicsContext.fillPolygon(
                new double[]{position.getX(), position.getX(),                     position.getX() + shape.getHeight()},
                new double[]{position.getY(), position.getY() + shape.getWidth(),  position.getY() + (shape.getWidth()/2)},
                3);


        graphicsContext.restore();


    }

    // Draws a line from a start to stop
    public static void drawLine(GraphicsContext graphicsContext, Color color, Point2D start, Point2D stop, double width) {
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(width);
        graphicsContext.strokeLine(start.getX(), start.getY(), stop.getX(), stop.getY());
    }
}