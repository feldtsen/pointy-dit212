package game.model.entity.projectile;

import game.model.entity.IEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.model.shape2d.IShape2D;
import game.util.Utils;
import javafx.geometry.Point2D;

// Missiles are a homing projectile which steers towards its target.
public class Missile extends Projectile<ICircle> {

    private IEntity<?> target;
    double minSpeed; // The minimum speed that the missile can have.
    //TODO: add movement behavior. (Are movement behaviours needed?)

    // The responsiveness of the missile to the movement of the target is dictated by the size of maxForce. The more
    // responsive the missile should be, the larger maxForce it should have.
    public Missile(Point2D position, double radius, double maxForce, double minSpeed, double maxSpeed, int strength, Point2D velocity, IEntity<?> target) {
        super(position, maxForce, maxSpeed, strength, velocity, new Circle(radius));
        this.minSpeed = minSpeed;
        this.target = target;
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
        if (target != null) {
            // Create acceleration vector pointed towards target.
            Point2D acceleration = target.getPosition().subtract(getPosition());

            // Set acceleration to maxForce.
            acceleration = Utils.setMagnitude(acceleration, getMaxForce());

            // Apply acceleration to velocity and apply lowerLimit at minSpeed.
            setVelocity(Utils.lowerLimit(getVelocity().add(acceleration), minSpeed));
        }
        super.update(delta, timeStep);
    }
}
