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

    @Override
    public void update(long delta) {

    }

    @Override
    public void move(Point2D offset) {
        position = position.add(offset);
    }

    @Override
    public void addForce(Point2D force) {
        acceleration = acceleration.add(force);
    }

    @Override
    public Point2D getVelocity() {
        return velocity;
    }

    @Override
    public Point2D setAcceleration() {
        return acceleration;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    @Override
    public void setAcceleration(Point2D acceleration) {

        return;
    }
}
