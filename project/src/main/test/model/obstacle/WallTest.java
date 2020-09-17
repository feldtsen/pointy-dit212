package model.obstacle;

import game.model.entity.Entity;
import game.model.entity.IEntity;
import game.model.entity.movable.MovableEntity;
import game.model.entity.obstacle.Wall;
import javafx.geometry.Point2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallTest {
    private Entity wall  = new Wall(new Point2D(50, 50), 50, 50);

    @Test
    public void testCheckCollisionNoCollision() {
        IEntity entity0 = new MovableEntity(new Point2D(100, 100), 10, 3, 3) {};  /* Bottom right of wall. */
        IEntity entity1 = new MovableEntity(new Point2D(40, 40), 10, 3, 3){};  /* Top left of wall. */
        IEntity entity2 = new MovableEntity(new Point2D(100, 40), 10, 3, 3){}; /* Top right of wall. */
        IEntity entity3 = new MovableEntity(new Point2D(40, 100), 10, 3, 3) {}; /* Bottom left of wall */


        assertFalse(wall.checkCollision(entity0) || wall.checkCollision(entity1) ||
                              wall.checkCollision(entity2) || wall.checkCollision(entity3));
    }

    @Test
    public void testCheckCollisionCollision() {
        IEntity entity0 = new MovableEntity(new Point2D(99, 99), 10, 3, 3) {}; /* Bottom right of wall */
        IEntity entity1 = new MovableEntity(new Point2D(41, 41), 10, 3, 3) {}; /* Top left of wall */
        IEntity entity2 = new MovableEntity(new Point2D(99, 41), 10, 3, 3) {}; /* Bottom left of wall */
        IEntity entity3 = new MovableEntity(new Point2D(99, 99), 10, 3, 3) {}; /* Bottom right of wall */

        assertTrue(wall.checkCollision(entity0) && wall.checkCollision(entity1) &&
                             wall.checkCollision(entity2) && wall.checkCollision(entity3));
    }
}
