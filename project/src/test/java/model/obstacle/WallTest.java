package model.obstacle;
import game.model.entity.obstacle.Wall;
import game.model.entity.player.Player;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.model.shape2d.Rectangle;
import game.services.EntityFactory;
import game.util.Shapes;
import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class WallTest {


    @Test
    public void TestProjectileCollision() {

        Wall wall = new Wall(new Point2D(0,0),1,2);
        IProjectile<ICircle> bullet = new Bullet(new Point2D(0,0), 1,0,0,0, new Point2D(0,0));

        assertTrue(wall.checkCollision(bullet) != null);

        bullet.setPosition(new Point2D(-0.5,-1));
        assertTrue(wall.checkCollision(bullet) != null);
        bullet.setPosition(new Point2D(0.5,-1));
        assertTrue(wall.checkCollision(bullet) != null);
        bullet.setPosition(new Point2D(-0.5,1));
        assertTrue(wall.checkCollision(bullet) != null);
        bullet.setPosition(new Point2D(0.5,1));
        assertTrue(wall.checkCollision(bullet) != null);


        bullet.setPosition(new Point2D(-1,-2));
        assertFalse(wall.checkCollision(bullet) != null);
        bullet.setPosition(new Point2D(1,-2));
        assertFalse(wall.checkCollision(bullet) != null);
        bullet.setPosition(new Point2D(-1,2));
        assertFalse(wall.checkCollision(bullet) != null);
        bullet.setPosition(new Point2D(1,2));
        assertFalse(wall.checkCollision(bullet) != null);

    }
}
