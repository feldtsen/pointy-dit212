package game.model.entity.projectile;

import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.shape2d.ITriangle;
import game.model.shape2d.Triangle;
import javafx.geometry.Point2D;

// Missiles are a homing projectile which steers towards its target.
public class Missile extends Projectile<ITriangle> {
    private IEntity<?> target;
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


    public boolean setTarget(IEntity<?> target){
        this.target = target;
        return true;    //TODO: Temporary, Change when invalid targets exist
    }

    public IEntity<?> getTarget(){
        return target;
    }

    @Override
    public void update(double delta, double timeStep) {
        movementBehaviour.apply(this, target);
        super.update(delta, timeStep);
    }
}
