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
        Point2D p1 = new Point2D(0, 0);

        Rectangle r2 = new Rectangle(1, 1, Math.PI/4);
        Point2D p2 = new Point2D(1.2, 0);

        Rectangle r3 = new Rectangle(0.5, 0.5, Math.PI/4);
        Point2D p3 = new Point2D(0, 0);

        assertTrue(Shapes.testCollision(r1, p1, r2, p2) && Shapes.testCollision(r1, p1, r3, p3));
    }

    @Test
    public void testRectangleEdgeCollision() {
        Rectangle r1 = new Rectangle(1, 1, 0);
        Point2D p1 = new Point2D(0, 0);

        Rectangle r2 = new Rectangle(1, 1, 0);
        Point2D p2 = new Point2D(1, 1);

        Rectangle r3 = new Rectangle(1, 1, 0);
        Point2D p3 = new Point2D(1, 0);

        Rectangle r4 = new Rectangle(1, 1, 0);
        Point2D p4 = new Point2D(-1, 0);

        Rectangle r5 = new Rectangle(1, 1, 0);
        Point2D p5 = new Point2D(0, -1);

        assertTrue(Shapes.testCollision(r1, p1, r2, p2) &&
                Shapes.testCollision(r1, p1, r2, p2) &&
                Shapes.testCollision(r1, p1, r3, p3) &&
                Shapes.testCollision(r1, p1, r4, p4) &&
                Shapes.testCollision(r1, p1, r5, p5));
    }

    @Test
    public void testRectangleNoCollision() {
        Rectangle r1 = new Rectangle(1, 1, 0);
        Point2D p1 = new Point2D(0, 0);

        Rectangle r2 = new Rectangle(1, 1, Math.PI/6);
        Point2D p2 = new Point2D(1, 1);

        Rectangle r3 = new Rectangle(1, 1, 0);
        Point2D p3 = new Point2D(0, 1.01);

        Rectangle r4 = new Rectangle(1, 1, Math.PI/6);
        Point2D p4 = new Point2D(-1, 1);

        Rectangle r5 = new Rectangle(1, 1, 0);
        Point2D p5 = new Point2D(0, -1.01);

        assertFalse(Shapes.testCollision(r1, p1, r2, p2) ||
                Shapes.testCollision(r1, p1, r3, p3) ||
                Shapes.testCollision(r1, p1, r4, p4) ||
                Shapes.testCollision(r1, p1, r5, p5));
    }

    @Test
    public void testRectangleCircleCollision() {
        Circle circle = new Circle(1);
        Point2D pc = new Point2D(0, 0);

        Rectangle rect1 = new Rectangle(1, 1, 0);
        Point2D pr1 = new Point2D(0, 0);

        Point2D pr2 = new Point2D(1, 1);

        Point2D pr3 = new Point2D(-1.5, 0);

        assertTrue(Shapes.testCollision(rect1, pr1, circle, pc) &&
                Shapes.testCollision(rect1, pr2, circle, pc) &&
                Shapes.testCollision(rect1, pr3, circle, pc));
    }

    @Test
    public void testRectangleCircleNoCollision() {
        Circle circle = new Circle(1);
        Point2D pc = new Point2D(0, 0);

        Rectangle rect1 = new Rectangle(1, 1, Math.PI/4);
        Point2D pr1 = new Point2D(1.1, 1.1);

        Rectangle rect2 = new Rectangle(1, 1, 0);
        Point2D pr2 = new Point2D(0, 1.51);

        Point2D pr3 = new Point2D(-1.1, -1.1);

        Point2D pr4 = new Point2D(0, -1.51);

        assertFalse(Shapes.testCollision(rect1, pr1, circle, pc) ||
                Shapes.testCollision(rect2, pr2, circle, pc) ||
                Shapes.testCollision(rect1, pr3, circle, pc) ||
                Shapes.testCollision(rect2, pr4, circle, pc));
    }

    @Test
    public void testRectangleCircleEdgeCollision() {
        Circle circle = new Circle(1);
        Point2D pc = new Point2D(0, 0);

        Rectangle rectangle = new Rectangle(1, 1, 0);

        Point2D pr1 = new Point2D(1.5, 0);
        Point2D pr2 = new Point2D(0, 1.5);
        Point2D pr3 = new Point2D(-1.5, 0);
        Point2D pr4 = new Point2D(0, -1.5);

        assertTrue(Shapes.testCollision(rectangle, pr1, circle, pc) &&
                Shapes.testCollision(rectangle, pr2, circle, pc) &&
                Shapes. testCollision(rectangle, pr3, circle, pc) &&
                Shapes.testCollision(rectangle, pr4, circle, pc));

    }

}
