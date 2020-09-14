package game.model.entity;

import game.model.IMovable;
import javafx.geometry.Point2D;

public abstract class MovableEntity extends Entity implements IMovable {
    private double maxForce;
    private double maxSpeed;

    private Point2D acceleration;
    private Point2D velocity;

    public MovableEntity(Point2D position, double radius, double maxForce, double maxSpeed) {
        super(position, radius);
        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;


    }

    public void move(Point2D offset) {
        position = position.add(offset);
    }

    public void addForce(Point2D force) {

        return;
    }

    public Point2D getVelocity() {

        return null;
    }

    public Point2D getAcceleration() {

        return null;
    }

    public void setPosition(Point2D position) {

        return;
    }

    public void setVelocity(Point2D velocity) {

        return;
    }

    public void getAcceleration(Point2D acceleration) {

        return;
    }
}
