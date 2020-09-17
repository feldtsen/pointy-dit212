package model.entity;

import game.model.ILiving;
import game.model.entity.movable.LivingEntity;
import javafx.geometry.Point2D;
import org.junit.Test;

import static org.junit.Assert.assertFalse;


public class LivingEntityTest {
    /* Helper method required for initializing an anonymous instance of the abstract class */
    public ILiving createLivingEntity(Point2D position, double radius, double maxForce, double maxSpeed, int hitPoints) {
        return new LivingEntity(position, radius, maxForce, maxSpeed, hitPoints) {

            @Override
            public void update(double delta) {
            }
        };
    }

    @Test
    public void testAlive(){
        ILiving e1 = createLivingEntity(new Point2D(0,0), 10,0,0,10);
        ILiving e2 = createLivingEntity(new Point2D(0,0), 10,0,0,0);
        assert(e1.isAlive());
        assertFalse(e2.isAlive());

    }




}