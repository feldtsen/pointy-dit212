package model.entity;

import game.model.entity.Entity;
import game.model.entity.IEntity;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntityTest {
    /* Helper method required for initializing an anonymous instance of the abstract class */
    public IEntity createEntity(Point2D position, double radius) {
        return new Entity(position, radius) {

            @Override
            public void update(double delta) {
            }
        };
    }

    @Test
    public void testCheckCollisionNoCollision() {
        IEntity e1 = createEntity(new Point2D(0, 0), 10);
        IEntity e2 = createEntity(new Point2D(30, 0), 10);
        assertFalse(e1.checkCollision(e2));
    }

    @Test
    public void testCheckCollisionCollision() {
        IEntity e1 = createEntity(new Point2D(0, 0), 10);
        IEntity e2 = createEntity(new Point2D(5, 5), 10);
        assertTrue(e1.checkCollision(e2));
    }

    /* If the entities are precisely beside each other, this is counted as a collision */
    @Test
    public void testCollisionBorderCollision() {
        IEntity e1 = createEntity(new Point2D(5, 0), 5);
        IEntity e2 = createEntity(new Point2D(15, 0), 5);
        assertTrue(e1.checkCollision(e2));
    }
}
