package game.model.behavior.movement;

import game.model.entity.IEntity;
import game.model.entity.movable.MovableEntity;
import game.util.Utils;
import javafx.geometry.Point2D;

public class SeekingBehaviour implements IMovementBehaviour {


    @Override
    public boolean apply(MovableEntity subject, IEntity object) {
        Point2D direction = object.getPosition().subtract(subject.getPosition());
        Point2D force = Utils.setMagnitude(direction, subject.getMaxForce());
        subject.addForce(force);
        return true;
    }
}
