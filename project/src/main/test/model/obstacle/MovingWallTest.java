package model.obstacle;


import game.model.entity.obstacle.MovingWall;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;


public class MovingWallTest {


    private MovingWall wallX;
    private MovingWall wallY;

    @Before
    public void setup() {
        wallX =  new MovingWall(new Point2D(200,500), new Point2D(800,500), 2,2, 50, 20);
        wallY =  new MovingWall(new Point2D(500,200), new Point2D(500,800), 2,2, 20, 50);

    }

    @Test
    public void testMovement() {

        boolean b = false;
        //Test x
        double xStart = wallX.getPosition().getX();
        wallX.update(1,1);
        assertFalse(xStart == wallX.getPosition().getX());

        while(true) {
            wallX.update(1,1);
            if (wallX.getPosition().getX() == xStart) {
                b = true;
                break;
            }
        }
        assert(b);

        b = false;
        while(true) {
            wallX.update(1,1);
            if (wallX.getPosition().getX() == xStart) {
                b = true;
                break;
            }
        }
        assert(b);

        //Test y
        double yStart = wallX.getPosition().getX();
        wallY.update(1,1);
        assertFalse(yStart == wallY.getPosition().getY());

        b = false;
        while(true) {
            wallY.update(1,1);
            if (wallY.getPosition().getY() == yStart) {
                b = true;
                break;
            }
        }
        assert(b);

        b = false;
        while(true) {
            wallY.update(1,1);
            if (wallY.getPosition().getX() == yStart) {
                b = true;
                break;
            }
        }
        assert(b);

    }
}
