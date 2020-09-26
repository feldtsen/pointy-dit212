package game.model;

import javafx.geometry.Point2D;

// IMovable is an interface for physics operations, to be applied on the object which implements the interface.
public interface IMovable extends IPositionable, IUpdatable {
    // Offsets the object by set amount.
    void move(Point2D offset);

    // force is added to the acceleration of the object.
    // force should be limited to the value of maxForce.
    void addForce(Point2D force);

    void setPosition(Point2D position);
    void setVelocity(Point2D velocity);
    void setAcceleration(Point2D acceleration);

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
