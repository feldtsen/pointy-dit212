/*
 * Authors: Joachim Pedersen, Mattias Oom, Anton Hildingsson
 *
 * Utils class for drawing to the screen. Used to add a layer of abstraction
 * between the view and javFX.
 */
package game.view.renderer;

import game.model.shape2d.*;
import game.util.Utils;
import game.view.ViewResourceLoader;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.ArcType;

public class RendererUtils {
    // Helper class for defining an object with coordinates and dimensions
    private static class Quartet {
        public double x;
        public double y;
        public double w;
        public double h;
        public Quartet(double x, double y, double w, double h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }
    // Given a graphics context, it will clear that graphics context
    public static void clear(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(
                0,
                0,
                graphicsContext.getCanvas().getWidth(),
                graphicsContext.getCanvas().getHeight()
        );
    }

    // Given a graphics context and a color, color that graphics context with the given color
    public static void setBackgroundColor(GraphicsContext graphicsContext, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(
                0,
                0,
                graphicsContext.getCanvas().getWidth(),
                graphicsContext.getCanvas().getHeight()
        );

    }

    // Draw arch
    public static void drawArc(GraphicsContext graphicsContext, LinearGradient color, ICircle shape, Point2D position, double angle) {
        Quartet scaled = scaleDrawData(graphicsContext, position.getX(), position.getY(), shape.getRadius(), shape.getRadius());

        // Saves  attributes, such as paint data and transform data
        graphicsContext.save();

        translate(graphicsContext, shape.getRotation(), scaled.x, scaled.y);

       graphicsContext.setFill(color);
       graphicsContext.fillArc(
               scaled.x - scaled.w,
               scaled.y - scaled.h,
               2 * scaled.w,
               2 * scaled.h,
               0,
               Utils.radianToDegrees(angle),
               ArcType.ROUND);
        graphicsContext.restore();
    }


    // Draws a circle to the screen
    public static void drawCircle(GraphicsContext graphicsContext, Color color,  ICircle shape, Point2D position) {
        Quartet scaled = scaleDrawData(graphicsContext, position.getX(), position.getY(), shape.getRadius(), shape.getRadius());

        graphicsContext.setFill(color);
        graphicsContext.fillOval(
                scaled.x - scaled.w,
                scaled.y - scaled.h,
                2 * scaled.w,
                2 * scaled.h
        );
    }
    // Draws a ring to the screen
    public static void drawRing(GraphicsContext graphicsContext, Color color, ICircle shape, Point2D position) {
        Quartet scaled = scaleDrawData(graphicsContext, position.getX(), position.getY(), shape.getRadius(), shape.getRadius());

        graphicsContext.setStroke(color);
        graphicsContext.strokeOval(
                scaled.x - scaled.w,
                scaled.y - scaled.h,
                2 * scaled.w,
                2 * scaled.h
        );
    }

    // Draws a rectangle to the screen
    public static void drawRectangle(GraphicsContext graphicsContext, Color color, IRectangle shape, Point2D position) {
        Quartet scaled = scaleDrawData(graphicsContext, position.getX(), position.getY(), shape.getWidth(), shape.getHeight());
        // Saves  attributes, such as paint data and transform data
        graphicsContext.save();

        translate(graphicsContext, shape.getRotation(), scaled.x, scaled.y);

        graphicsContext.setFill(color);
        graphicsContext.fillRect(
                scaled.x - (scaled.w/2),
                scaled.y - (scaled.h/2),
                scaled.w,
                scaled.h
        );

        graphicsContext.restore();
    }

    private static final double[] xs = new double[3];
    private static final double[] ys = new double[3];

    public static void drawTriangle(GraphicsContext graphicsContext, Color color, ITriangle shape, Point2D position) {
        Quartet scaled = scaleDrawData(graphicsContext, position.getX(), position.getY(), shape.getWidth(), shape.getHeight());

        // Saves  attributes, such as paint data and transform data
        graphicsContext.save();

        translate(graphicsContext, shape.getRotation(), scaled.x, scaled.y);

        xs[0] = scaled.x - scaled.w /2;
        xs[1] = scaled.x + scaled.w /2;
        xs[2] = scaled.x;


        ys[0] = scaled.y - scaled.h /2;
        ys[1] = scaled.y - scaled.h /2;
        ys[2] = scaled.y + scaled.h /2;

        graphicsContext.setFill(color);
        graphicsContext.fillPolygon(xs, ys, xs.length);

        graphicsContext.restore();
    }

    // Draws a line from a start to stop
    public static void drawLine(GraphicsContext graphicsContext, Color color, Point2D start, Point2D stop, double width) {
        double scaledWidth = scaleRespectToWidth(graphicsContext, width);

        double scaledStartX = scaleRespectToWidth(graphicsContext, start.getX());
        double scaledStartY = scaleRespectToHeight(graphicsContext, start.getY());

        double scaledStopX = scaleRespectToWidth(graphicsContext, stop.getX());
        double scaledStopY = scaleRespectToHeight(graphicsContext, stop.getY());

        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(scaledWidth);
        graphicsContext.strokeLine(scaledStartX, scaledStartY, scaledStopX, scaledStopY);
    }

    public static void setCorrectObstacleRotation(GraphicsContext graphicsContext, IShape2D shape, Point2D position) {
        Quartet scaled = scaleDrawData(graphicsContext, position.getX(), position.getY(), shape.getWidth(), shape.getHeight());
        graphicsContext.translate(scaled.x, scaled.y);
        graphicsContext.rotate(Utils.radianToDegrees(shape.getRotation()) + 90);
        graphicsContext.translate(-scaled.x, -scaled.y);
    }

    private static void translate(GraphicsContext graphicsContext, double shapeRotation, double xPosition, double yPosition) {
        // Translates the shape to a position
        graphicsContext.translate(xPosition, yPosition);

        // Rotates the current transform
        graphicsContext.rotate(Utils.radianToDegrees(shapeRotation) - 90);

        // Translate to where you want it
        graphicsContext.translate(-xPosition, -yPosition);
    }

    public static Quartet scaleDrawData(GraphicsContext graphicsContext, double x, double y, double w, double h) {
        return new Quartet(
                scaleRespectToWidth(graphicsContext, x),
                scaleRespectToHeight(graphicsContext, y),
                scaleRespectToWidth(graphicsContext, w),
                scaleRespectToHeight(graphicsContext, h)
        );
    }

    // Given the graphics context and the value you want to update, returns the value scaled to the current width
    // relative to the initial width
    public static double scaleRespectToWidth (GraphicsContext graphicsContext, double oldValue) {
        return Utils.map(oldValue, 0, ViewResourceLoader.INITIAL_WIDTH, 0, graphicsContext.getCanvas().getWidth());
    }
    public static double scaleRespectToHeight (GraphicsContext graphicsContext, double oldValue) {
        return Utils.map(oldValue, 0, ViewResourceLoader.INITIAL_HEIGHT, 0, graphicsContext.getCanvas().getHeight());
    }


}
