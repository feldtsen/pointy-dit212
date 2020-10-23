/*
 * Authors: Anton Hildingsson, Mattias Oom
 *
 * This movement behavior will have the user of the behavior seek (move towards) the target entity.
 * The user of this ability will always move towards the tartget, no matter what.
 */

package game.model.behavior.movement;

import game.model.entity.IEntity;
import game.model.entity.movable.IMovable;
import game.util.Utils;
import javafx.geometry.Point2D;

public class SeekingBehaviour implements IMovementBehaviour {
    @Override
    public boolean apply(IMovable<?> subject, IEntity<?> object) {
        // Calculate a direction vector pointing towards the target
        Point2D direction = object.getPosition().subtract(subject.getPosition());
        // Set the magnitude of the vector to the max force of the user, and apply.
        Point2D force = Utils.setMagnitude(direction, subject.getMaxForce());
        subject.addForce(force);
        return true;
    }
}
