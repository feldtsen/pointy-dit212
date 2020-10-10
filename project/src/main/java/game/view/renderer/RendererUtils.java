package game.view.renderer;

import game.model.shape2d.*;
import game.util.Utils;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RendererUtils {
    private static GraphicsContext gc;

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
    public static void drawCircle(GraphicsContext graphicsContext, Color color,  ICircle circle, Point2D position) {
        gc = graphicsContext;

        double scaledRadiusW = scaleRespectToWidth(circle.getRadius());
        double scaledRadiusH = scaleRespectToHeight(circle.getRadius());

        double scaledXPosition   = scaleRespectToWidth(position.getX());
        double scaledYPosition   = scaleRespectToHeight(position.getY());

        graphicsContext.setFill(color);
        graphicsContext.fillOval(
                scaledXPosition - scaledRadiusW,
                scaledYPosition - scaledRadiusH,
                2 * scaledRadiusW,
                2 * scaledRadiusH
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

        double scaledShapeWidth  = scaleRespectToWidth(shape.getWidth());
        double scaledShapeHeight = scaleRespectToHeight(shape.getHeight());

        double scaledXPosition   = scaleRespectToWidth(position.getX());
        double scaledYPosition   = scaleRespectToHeight(position.getY());

        SaveAndTranslate(scaledShapeWidth, scaledShapeHeight, shape.getRotation(), scaledXPosition, scaledYPosition);

        graphicsContext.setFill(color);
        graphicsContext.fillRect(scaledXPosition, scaledYPosition, scaledShapeWidth, scaledShapeHeight);

        graphicsContext.restore();
    }


    public static void drawTriangle(GraphicsContext graphicsContext, Color color, ITriangle shape, Point2D position) {
        gc = graphicsContext;

        double scaledShapeWidth  = scaleRespectToWidth(shape.getWidth());
        double scaledShapeHeight = scaleRespectToHeight(shape.getHeight());

        double scaledXPosition   = scaleRespectToWidth(position.getX());
        double scaledYPosition   = scaleRespectToHeight(position.getY());

        SaveAndTranslate(scaledShapeWidth, scaledShapeHeight, shape.getRotation(), scaledXPosition, scaledYPosition);

        graphicsContext.setFill(color);
        graphicsContext.fillPolygon(
                new double[]{scaledXPosition, scaledXPosition + scaledShapeWidth, scaledXPosition + scaledShapeWidth/2},

                new double[]{scaledYPosition, scaledYPosition              , scaledYPosition + scaledShapeHeight},
                3);


        graphicsContext.restore();
    }

    // Draws a line from a start to stop
    public static void drawLine(GraphicsContext graphicsContext, Color color, Point2D start, Point2D stop, double width) {
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(width);
        graphicsContext.strokeLine(start.getX(), start.getY(), stop.getX(), stop.getY());
    }

    private static void SaveAndTranslate(double shapeWidth, double shapeHeight, double shapeRotation, double xPosition, double yPosition) {
        // Saves  attributes, such as paint data and transform data
        gc.save();

        // Translates the shape to a position
        gc.translate(xPosition, yPosition);

        // Rotates the current transform
        gc.rotate(Utils.radianToDegrees(shapeRotation) - 90);

        // Translate to where you want it
        gc.translate(-xPosition - shapeWidth/2, -yPosition - shapeHeight/2);
    }

    private static double scaleRespectToWidth (double oldValue) {
        return Utils.map(oldValue, 0, 1200, 0, gc.getCanvas().getWidth());
    }
    private static double scaleRespectToHeight (double oldValue) {
        return Utils.map(oldValue, 0, 1200*.5625, 0, gc.getCanvas().getHeight());
    }


}
