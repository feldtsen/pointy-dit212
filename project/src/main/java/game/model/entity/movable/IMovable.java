package game.model.entity.movable;

import game.model.IUpdatable;
import game.model.entity.IEntity;
import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

// IMovable is an interface for physics operations, to be applied on the object which implements the interface.
public interface IMovable<T extends IShape2D> extends IUpdatable, IEntity<T> {
    // Offsets the object by set amount.
    void move(Point2D offset);

    // force is added to the acceleration of the object.
    // force should be limited to the value of maxForce.
    void addForce(Point2D force);

    void setPosition(Point2D position);
    void setVelocity(Point2D velocity);
    void setAcceleration(Point2D acceleration);
    void setMinSpeed(double minSpeed);
    void setMaxSpeed(double maxSpeed);

    // Friction is applied to the velocity. The friction should depend on the velocity itself.
    void setFriction(double friction);

    Point2D getVelocity();
    Point2D getAcceleration();
    double getMaxSpeed();
    double getMaxForce();

    // Update will add the acceleration to the velocity of the object, while limiting the velocity to maxSpeed.
    // Friction should be applied, and the position should be updated.
    // At the end of the update method, the acceleration should be reset to 0.
    void update(double delta, double timeStep);
}
