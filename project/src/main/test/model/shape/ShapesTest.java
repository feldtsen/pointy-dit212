package model.shape;

import game.model.shape2d.Circle;
import game.model.shape2d.Rectangle;
import game.model.shape2d.Shapes;
import javafx.geometry.Point2D;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
public class ShapesTest {

    @Test
    public void testCircleCollision(){
        Circle c1 = new Circle(5);
        Circle c2 = new Circle(5);
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(9,0);

        assertTrue(Shapes.testCollision(c1,p1,c2,p2));

    }

    @Test
    public void testCircleEdgeCollision(){
        Circle c1 = new Circle(4);
        Circle c2 = new Circle(3);
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(7,0);

        assertTrue(Shapes.testCollision(c1,p1,c2,p2));

    }

    @Test
    public void testCircleNoCollision(){
        Circle c1 = new Circle(50);
        Circle c2 = new Circle(5);
        Point2D p1 = new Point2D(120,0);
        Point2D p2 = new Point2D(9,0);

        assertFalse(Shapes.testCollision(c1,p1,c2,p2));

    }

    @Test
    public void testRectangleCollision() {
        Rectangle r1 = new Rectangle(1, 1, 0);
        Rectangle r2 = new Rectangle(1, 1, 0);

        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(1, 1);

        assertTrue(Shapes.testCollision(r1, p1, r2, p2));
    }

    @Test
    public void testRectangleNoCollision() {
        Rectangle r1 = new Rectangle(1, 1, 0);
        Rectangle r2 = new Rectangle(1, 1, Math.PI/6);

        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(1, 1);

        assertFalse(Shapes.testCollision(r1, p1, r2, p2));
    }

}
