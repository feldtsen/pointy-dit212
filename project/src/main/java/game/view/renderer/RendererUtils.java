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
    public static void drawCircle(GraphicsContext graphicsContext, Color color,  ICircle circle, Point2D position) {
        double scaledRadiusW = scaleRespectToWidth(graphicsContext, circle.getRadius());
        double scaledRadiusH = scaleRespectToHeight(graphicsContext, circle.getRadius());

        double scaledXPosition   = scaleRespectToWidth(graphicsContext, position.getX());
        double scaledYPosition   = scaleRespectToHeight(graphicsContext, position.getY());

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
        double scaledShapeWidth  = scaleRespectToWidth(graphicsContext, shape.getWidth());
        double scaledShapeHeight = scaleRespectToHeight(graphicsContext, shape.getHeight());

        double scaledXPosition   = scaleRespectToWidth(graphicsContext, position.getX());
        double scaledYPosition   = scaleRespectToHeight(graphicsContext, position.getY());

        SaveAndTranslate(graphicsContext, scaledShapeWidth, scaledShapeHeight, shape.getRotation(), scaledXPosition, scaledYPosition);

        graphicsContext.setFill(color);
        graphicsContext.fillRect(scaledXPosition, scaledYPosition, scaledShapeWidth, scaledShapeHeight);

        graphicsContext.restore();
    }

    private static final double[] xs = new double[3];
    private static final double[] ys = new double[3];


    public static void drawTriangle(GraphicsContext graphicsContext, Color color, ITriangle shape, Point2D position) {
        double scaledShapeWidth  = scaleRespectToWidth(graphicsContext, shape.getWidth());
        double scaledShapeHeight = scaleRespectToHeight(graphicsContext, shape.getHeight());

        double scaledXPosition   = scaleRespectToWidth(graphicsContext, position.getX());
        double scaledYPosition   = scaleRespectToHeight(graphicsContext, position.getY());

        SaveAndTranslate(graphicsContext, scaledShapeWidth, scaledShapeHeight, shape.getRotation(), scaledXPosition, scaledYPosition);

        xs[0] = scaledXPosition;
        xs[1] = scaledXPosition + scaledShapeWidth;
        xs[2] = scaledXPosition + scaledShapeWidth / 2;


        ys[0] = scaledYPosition;
        ys[1] = scaledYPosition;
        ys[2] = scaledYPosition + scaledShapeHeight;

        graphicsContext.setFill(color);
        graphicsContext.fillPolygon(xs, ys, xs.length);


        graphicsContext.restore();
    }

    // Draws a line from a start to stop
    public static void drawLine(GraphicsContext graphicsContext, Color color, Point2D start, Point2D stop, double width) {
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(width);
        graphicsContext.strokeLine(start.getX(), start.getY(), stop.getX(), stop.getY());
    }

    private static void SaveAndTranslate(GraphicsContext graphicsContext, double shapeWidth, double shapeHeight, double shapeRotation, double xPosition, double yPosition) {
        // Saves  attributes, such as paint data and transform data
        graphicsContext.save();

        // Translates the shape to a position
        graphicsContext.translate(xPosition, yPosition);

        // Rotates the current transform
        graphicsContext.rotate(Utils.radianToDegrees(shapeRotation) - 90);

        // Translate to where you want it
        graphicsContext.translate(-xPosition - shapeWidth/2, -yPosition - shapeHeight/2);
    }

    public static double scaleRespectToWidth (GraphicsContext graphicsContext, double oldValue) {
        return Utils.map(oldValue, 0, 1200, 0, graphicsContext.getCanvas().getWidth());
    }
    public static double scaleRespectToHeight (GraphicsContext graphicsContext, double oldValue) {
        return Utils.map(oldValue, 0, 1200*.5625, 0, graphicsContext.getCanvas().getHeight());
    }


}
