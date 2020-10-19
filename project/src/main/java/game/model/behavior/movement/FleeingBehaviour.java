/*
 * Authors: Simon Genne, Anton Hildingsson
 */

package game.model.behavior.movement;

import game.model.entity.IEntity;
import game.model.entity.movable.IMovable;
import javafx.geometry.Point2D;

import java.util.Random;

// A behavior for defining entities which moves away from its target if the target comes too close
public class FleeingBehaviour implements IMovementBehaviour {
    // The closest distance that the subject wants to be from the object.
    private final double closestDistance;

    // The furthest distance that the subject wants to be from the object.
    private final double furthestDistance;

    // The sideways direction the subject will move from the object.
    private Point2D sidewaysDirection;

    // For generating a new direction in randDirection
    private final Random random = new Random();

    public FleeingBehaviour(double closestDistance, double furthestDistance) {
        this.closestDistance = closestDistance;
        this.furthestDistance = furthestDistance;
    }

    // Applies a force onto the subject. The direction will depend on the distance from the object.
    @Override
    public boolean apply(IMovable<?> subject, IEntity<?> object) {

        // vector pointing from object to subject.
        Point2D posDifference = subject.getPosition().subtract(object.getPosition());

        // If subject is to close to the object.
        if (posDifference.magnitude() < closestDistance) {
            sidewaysDirection = null;

            // Add force away from object.
            subject.addForce(posDifference);
        } else if (posDifference.magnitude() > furthestDistance) {
            sidewaysDirection = null;

            // Add force towards object.
            subject.addForce(posDifference.multiply(-1));
        } else {
            if (sidewaysDirection == null) {
                // Set sideways direction if it hasn't already been set.
                sidewaysDirection = randomDirection(posDifference);
            }

            // Move in sideways direction.
            subject.addForce(sidewaysDirection);
        }
        return true;
    }

    // Returns a vector pointing in the clockwise- or counter clockwise direction from the given facing direction.
    private Point2D randomDirection(Point2D facing) {
        if (random.nextInt(2) == 1) {
            return new Point2D(facing.getY(), -facing.getX());
        } else {
            return new Point2D(-facing.getY(), facing.getX());
        }
    }
}
