package game.model.behavior.movement;

import game.model.entity.IEntity;
import game.model.entity.movable.IMovable;
import javafx.geometry.Point2D;

import java.util.Random;

public class FleeingBehaviour implements IMovementBehaviour {
    private double closestDistance;    // The closest distance that the subject wants to be from the object.
    private double furthestDistance;   // The furthest distance that the subject wants to be from the object.
    private Point2D sidewaysDirection; // The sideways direction the subject will move from the object.

    private Random rand = new Random(); // For generating a new direction in randDirection


    public FleeingBehaviour(double closestDistance, double furthestDistance) {
        this.closestDistance = closestDistance;
        this.furthestDistance = furthestDistance;
    }

    @Override
    // Applies a force onto the subject. The direction will depend on the distance from the object.
    public boolean apply(IMovable<?> subject, IEntity<?> object) {

        // vector pointing from object to subject.
        Point2D posDifference = subject.getPosition().subtract(object.getPosition());

        // If subject is to close to the object.
        if (posDifference.magnitude() < closestDistance) {
            sidewaysDirection = null;

            // Add force away from object.
            subject.addForce(posDifference);
        }
        else if (posDifference.magnitude() > furthestDistance) {
            sidewaysDirection = null;

            // Add force towards object.
            subject.addForce(posDifference.multiply(-1));
        }
        else {
            if (sidewaysDirection == null) {
                // Set sideways direction if it hasn't already been set.
                sidewaysDirection = randDirection(posDifference);
            }

            // Move in sideways direction.
            subject.addForce(sidewaysDirection);
        }
        return true;
    }

    // Returns a vector pointing in the clockwise- or counter clockwise direction from the given facing direction.
    private Point2D randDirection(Point2D facing) {
        if (rand.nextInt(2) == 1) {
            return new Point2D(facing.getY(), -facing.getX());
        }
        else {
            return new Point2D(-facing.getY(), facing.getX());
        }
    }
}
