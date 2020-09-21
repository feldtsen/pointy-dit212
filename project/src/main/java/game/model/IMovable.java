package game.model;

import javafx.geometry.Point2D;

public interface IMovable extends IPositionable {
    void move(Point2D offset);
    void addForce(Point2D force);

    Point2D getVelocity();
    Point2D getAcceleration();
    double getMaxSpeed();
    double getMaxForce();

    void setPosition(Point2D position);
    void setVelocity(Point2D velocity);
    void setAcceleration(Point2D acceleration);

    void setFriction(double friction);
}
