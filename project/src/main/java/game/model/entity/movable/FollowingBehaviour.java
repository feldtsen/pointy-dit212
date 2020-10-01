package game.model.entity.movable;

import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import javafx.geometry.Point2D;

// Movement behaviour for moving a subject towards an object.
public class FollowingBehaviour implements IMovementBehaviour {

    @Override
    // Adds a force to the subject in the direction of the object.
    public boolean apply(IMovable<?> subject, IEntity<?> object) {
        if (object == null) return false;

        // Create and apply acceleration vector pointed towards object.
        Point2D acceleration = object.getPosition().subtract(subject.getPosition());
        subject.addForce(acceleration);

        return true;
    }
}
