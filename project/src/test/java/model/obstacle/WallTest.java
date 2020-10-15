package model.obstacle;
import game.model.entity.obstacle.Wall;
import game.model.entity.player.Player;
import game.model.entity.projectile.Bullet;
import game.model.shape2d.Circle;
import game.model.shape2d.Rectangle;
import game.services.EntityFactory;
import game.util.Shapes;
import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class WallTest {


    @Test
    public void TestCollision() {

        // TODO: Fix shape collision first!
        /*
        Wall wall = new Wall(new Point2D(0,0),10,20);
        Player player = EntityFactory.basicPlayer(0,0);
        Bullet bullet = new Bullet(new Point2D(0,0), 10,0,0,0,new Point2D(0,0));

        assertTrue(wall.checkCollision(player));
        assertTrue(wall.checkCollision(bullet));

        player.setPosition(new Point2D(10,10));
        bullet.setPosition(new Point2D(10,10));
        assertTrue(wall.checkCollision(player));
        assertTrue(wall.checkCollision(bullet));

        player.setPosition(new Point2D(20,40));
        bullet.setPosition(new Point2D(10,20));
        assertTrue(wall.checkCollision(player));
        assertTrue(wall.checkCollision(bullet));

        player.setPosition(new Point2D(10,30));
        bullet.setPosition(new Point2D(10,30));
        assertFalse(wall.checkCollision(player));
        assertFalse(wall.checkCollision(bullet));

         */

    }
}
