package model.obstacle;


import game.model.entity.obstacle.MovingWall;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class MovingWallTest {


    private MovingWall wallX;
    private MovingWall wallY;

    @Before
    public void setup() {
        wallX =  new MovingWall(new Point2D(0,0), new Point2D(10,0), 2,2, 50, 20);
        wallY =  new MovingWall(new Point2D(0,0), new Point2D(0,10), 2,2, 20, 50);
    }

    @Test
    public void testMovementX() {

        boolean b = false;

        double xStart = wallX.getPosition().getX();
        double yStart = wallX.getPosition().getY();

        wallX.update(1,1);
        assertNotEquals(xStart, wallX.getPosition().getX(), 0.0);
        assertTrue(yStart == wallX.getPosition().getY());

        while(true) {
            wallX.update(1,1);
            if (wallX.getPosition().getX() == xStart) {
                b = true;
                break;
            }
        }
        assertTrue(b);
        assertTrue(yStart == wallX.getPosition().getY());

        b = false;
        while(true) {
            wallX.update(1,1);
            if (wallX.getPosition().getX() == xStart) {
                b = true;
                break;
            }
        }
        assertTrue(b);
        assertTrue(yStart == wallX.getPosition().getY());



    }
    @Test
    public void testMovementY() {

        boolean b = false;

        double xStart = wallY.getPosition().getX();
        double yStart = wallY.getPosition().getY();

        wallY.update(1,1);
        assertNotEquals(yStart, wallY.getPosition().getY(), 0.0);
        assertTrue(xStart == wallY.getPosition().getX());


        b = false;
        while(true) {
            wallY.update(1,1);
            if (wallY.getPosition().getY() == yStart) {
                b = true;
                break;
            }
        }
        assertTrue(b);
        assertTrue(xStart == wallY.getPosition().getX());

        b = false;
        while(true) {
            wallY.update(1,1);
            if (wallY.getPosition().getX() == yStart) {
                b = true;
                break;
            }
        }
        assertTrue(b);
        assertTrue(xStart == wallY.getPosition().getX());
    }
}
