/*
 * Authors: Simon Genne, Mattias Oom, Anton Hildingsson, Joachim Pedersen, Erik Magnusson
 *
 * Missiles are a homing projectile which steers towards its target. The projectiles
 * use a movement behavior which should cause the missile to move towards the target.
 */

package game.model.entity.projectile;

import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.shape2d.ITriangle;
import game.model.shape2d.Triangle;
import javafx.geometry.Point2D;

public class Missile extends Projectile<ITriangle> {
    // The target of the missile
    private IEntity<?> target;

    // The movement behavior of the missile. Typically some kind of seeking behavior
    private final IMovementBehaviour movementBehaviour;

    // The responsiveness of the missile to the movement of the target is dictated by the size of maxForce. The more
    // responsive the missile should be, the larger maxForce it should have.
    public Missile(Point2D position, double width, double height, double maxForce, double minSpeed, double maxSpeed, int strength,
                   Point2D velocity, IEntity<?> target, IMovementBehaviour movementBehaviour) {
        super(position, maxForce, maxSpeed, strength, velocity, new Triangle(width, height, 0));
        this.target = target;
        this.movementBehaviour = movementBehaviour;
        setMinSpeed(minSpeed);
    }


    // Sets the target of the missile
    public boolean setTarget(IEntity<?> target){
        // Return false if the target is the missile itself.
        if(target.equals(this)) return false;
        this.target = target;
        return true;
    }

    // Returns the target of the missile
    public IEntity<?> getTarget(){
        return target;
    }

    // Updates the missile
    @Override
    public void update(double delta, double timeStep) {
        // Apply the movement behavior
        movementBehaviour.apply(this, target);
        super.update(delta, timeStep);
    }
}
