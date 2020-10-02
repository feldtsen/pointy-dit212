package model.entity;

import game.model.entity.movable.ILiving;
import game.model.entity.movable.LivingEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;
import org.junit.Test;

import static org.junit.Assert.assertFalse;


public class LivingEntityTest {
    /* Helper method required for initializing an anonymous instance of the abstract class */
    public ILiving createLivingEntity(Point2D position, double maxForce, double maxSpeed, int hitPoints, IShape2D shape, int strength) {
        return new LivingEntity<>(position, maxForce, maxSpeed, hitPoints, shape, strength) {

            @Override
            public void update(double delta, double timeStep) {
            }
        };
    }

    @Test
    public void testAlive(){
        ILiving e1 = createLivingEntity(new Point2D(0,0), 10,0,10, new Circle(10),0);
        ILiving e2 = createLivingEntity(new Point2D(0, 0),10,0,0,new Circle(10),0);
        assert(e1.isAlive());
        assertFalse(e2.isAlive());

    }




}