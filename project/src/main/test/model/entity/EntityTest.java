package model.entity;

import game.model.entity.Entity;
import game.model.shape2d.*;
import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntityTest {
    Entity<IRectangle> testRectangle = createEntityRectangle(new Point2D(0, 0), 1, 1, 0);
    Entity<ICircle> testCircle = createEntityCircle(new Point2D(0, 0), 1);

    // Helper method required for initializing an anonymous instance of the abstract class with a circle shape.
    public Entity<ICircle> createEntityCircle(Point2D position, double radius) {
        return new Entity<ICircle>(position, new Circle(radius)) {

            @Override
            public void update(double delta) {
            }
        };
    }

    // Helper method for initializing an anonymous instance of the abstract class with a rectangle shape.
    public Entity<IRectangle> createEntityRectangle(Point2D position, double width, double height, double rotation) {
        return new Entity<IRectangle>(position, new Rectangle(width, height, rotation)) {
            @Override
            public void update(double delta) {
            }
        };
    }

    @Test
    public void testCheckCollisionNoCollision() {
        Entity<ICircle> circle0 = createEntityCircle(new Point2D(2.0001, 0), 1);
        Entity<ICircle> circle1 = createEntityCircle(new Point2D(1.50001, 0), 1);
        Entity<ICircle> circle2 = createEntityCircle(new Point2D(0, 1.50001), 1);

        Entity<IRectangle> rectangle0 = createEntityRectangle(new Point2D(-1, -1), 1, 1, Math.PI/4);
        Entity<IRectangle> rectangle1 = createEntityRectangle(new Point2D(1, 1), 1, 1, Math.PI/4);
        Entity<IRectangle> rectangle2 = createEntityRectangle(new Point2D(0, 1.0001), 1, 1, 0);
        Entity<IRectangle> rectangle3 = createEntityRectangle(new Point2D(0, -1.0001), 1, 1, 0);


        assertFalse(testCircle.checkCollision(circle0));
        assertFalse(testRectangle.checkCollision(circle1));
        assertFalse(testRectangle.checkCollision(circle2));
        assertFalse(testRectangle.checkCollision(rectangle0));
        assertFalse(testRectangle.checkCollision(rectangle1));
        assertFalse(testRectangle.checkCollision(rectangle2));
        assertFalse(testRectangle.checkCollision(rectangle3));
    }

    @Test
    public void testCheckCollisionCollision() {
        Entity<ICircle> circle0 = createEntityCircle(new Point2D(1.4, 0), 1);
        Entity<IRectangle> rectangle0 = createEntityRectangle(new Point2D(0.75, 0), 1, 1, 0);

        assertTrue(testCircle.checkCollision(circle0));
        assertTrue(testCircle.checkCollision(rectangle0));
        assertTrue(testRectangle.checkCollision(circle0));
        assertTrue(testRectangle.checkCollision(rectangle0));
    }

    @Test
    public void testCheckCollisionEdge() {
        Entity<ICircle> circle0 = createEntityCircle(new Point2D(2, 0), 1);
        Entity<ICircle> circle1 = createEntityCircle(new Point2D(1.5, 0), 1);

        Entity<IRectangle> rectangle0 = createEntityRectangle(new Point2D(1, 1), 1, 1, 0);
        Entity<IRectangle> rectangle1 = createEntityRectangle(new Point2D(-1, 0), 1, 1, 0);

        assertTrue(testCircle.checkCollision(circle0));
        assertTrue(testRectangle.checkCollision(circle1));
        assertTrue(testRectangle.checkCollision(rectangle0));
        assertTrue(testRectangle.checkCollision(rectangle1));
    }
}
