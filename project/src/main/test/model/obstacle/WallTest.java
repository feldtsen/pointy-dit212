package model.obstacle;

import game.model.entity.Entity;
import game.model.entity.IEntity;
import game.model.entity.obstacle.Wall;
import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class WallTest {
    private Entity wall  = new Wall(new Point2D(50, 50), 50, 50);

    /* Helper method required for initializing an anonymous instance of the abstract class */
    public IEntity createEntity(Point2D position, double radius) {
        return new Entity(position, radius) {
            /* Dummy method, will be tested in implementation of abstract class entity */
            @Override
            public boolean handleCollision(IEntity entity) {
                return false;
            }

            @Override
            public void update(double delta) {
            }
        };
    }

    @Test
    public void testCheckCollisionNoCollision() {
        IEntity entity = createEntity(new Point2D(100, 100),10);
        assertFalse(wall.checkCollision(entity));
    }

    @Test
    public void testCheckCollisionCollision() {
        IEntity entity = createEntity(new Point2D(99, 99), 10);
        assertTrue(wall.checkCollision(entity));
    }
}
