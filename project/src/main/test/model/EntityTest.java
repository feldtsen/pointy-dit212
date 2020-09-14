package model;

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
            /* Dummy method, will be tested in implementation of abstract class entity */
            @Override
            public boolean handleCollision(IEntity entity) {
                return false;
            }
        };
    }

    @Test
    public void testCheckCollisionNoCollision() {
        IEntity e1 = createEntity(new Point2D(0, 0), 10);
        IEntity e2 = createEntity(new Point2D(20, 0), 10);
        assertFalse(e1.checkCollision(e2));
    }
}
