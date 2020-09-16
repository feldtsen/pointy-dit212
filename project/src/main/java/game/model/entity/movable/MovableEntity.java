package game.model.entity.movable;

import game.model.IMovable;
import game.model.entity.Entity;
import game.util.Utils;
import javafx.geometry.Point2D;

public abstract class MovableEntity extends Entity implements IMovable {
    private final double maxForce;
    private final double maxSpeed;

    private Point2D acceleration;
    private Point2D velocity;

    public MovableEntity(Point2D position, double radius, double maxForce, double maxSpeed) {
        this(position, new Point2D(0, 0), radius, maxForce, maxSpeed);
    }

    public MovableEntity(Point2D position, Point2D velocity, double radius, double maxForce, double maxSpeed) {
        super(position, radius);

        this.velocity = velocity;
        this.acceleration = new Point2D(0, 0);

        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;
    }

    /* Takes change in time in nanoseconds since last update */
    @Override
    public void update(double delta) {
        /* Limit the acceleration to maxForce, and multiply with the delta time value */
        setAcceleration(Utils.limit(acceleration, maxForce).multiply(delta));

        /* Add acceleration to velocity, and limit the velocity to max speed */
        setVelocity(Utils.limit(velocity.add(acceleration), maxSpeed));

        /* Add velocity to position */
        setPosition(position.add(velocity));

        /* Reset acceleration */
        setAcceleration(new Point2D(0, 0));
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
    public Point2D getAcceleration() {
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
        this.acceleration = acceleration;
    }

}