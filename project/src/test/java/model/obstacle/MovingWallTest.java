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

        double xStart = wallX.getPosition().getX();
        double yStart = wallX.getPosition().getY();

        // Test to see if position changes when updating
        wallX.update(1,1);
        assertNotEquals(xStart, wallX.getPosition().getX(), 0.0);
        assertEquals(yStart, wallX.getPosition().getY(), 0.0);

        // Check that speed direction changes when reaching end and starting position
        Point2D oldSpeed = wallX.getVelocity();
        wallX.setPosition(new Point2D(0,10));
        wallX.update(1,1);
        assertNotSame(oldSpeed, wallX.getVelocity());
        oldSpeed = wallX.getVelocity();
        wallX.setPosition(new Point2D(0,0));
        wallX.update(1,1);
        assertNotSame(oldSpeed, wallX.getVelocity());


    }
    @Test
    public void testMovementY() {

        double xStart = wallY.getPosition().getX();
        double yStart = wallY.getPosition().getY();

        // Test to see if position changes when updating
        wallY.update(1,1);
        assertNotEquals(yStart, wallY.getPosition().getY(), 0.0);
        assertEquals(xStart, wallY.getPosition().getX(), 0.0);

        // Check that speed direction changes when reaching end and starting position
        Point2D oldSpeed = wallY.getVelocity();
        wallY.setPosition(new Point2D(0,10));
        wallY.update(1,1);
        assertNotSame(oldSpeed, wallY.getVelocity());
        oldSpeed = wallY.getVelocity();
        wallY.setPosition(new Point2D(0,0));
        wallY.update(1,1);
        assertNotSame(oldSpeed, wallY.getVelocity());


    }


}
