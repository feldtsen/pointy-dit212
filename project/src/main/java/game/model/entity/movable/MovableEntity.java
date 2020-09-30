package game.model.entity.movable;

import game.model.entity.Entity;
import game.model.shape2d.IShape2D;
import game.util.Utils;
import javafx.geometry.Point2D;

// A movable entity is any entity with a velocity and acceleration, that is, any entity affected by game physics
public abstract class MovableEntity<T extends IShape2D> extends Entity<T> implements IMovable<T> {
    // Max force is the maximum acceleration which can be applied to the movable entity (using the addForce method)
    private final double maxForce;
    // Max speed is the maximum speed which can be reached by manipulating the entity using the addForce method
    private final double maxSpeed;

    private Point2D acceleration;
    private Point2D velocity;
    private double minSpeed = 0;

    // Friction is applied each update, effectively reducing the velocity of the movable entity with the set amount.
    private double friction;

    public MovableEntity(Point2D position, double maxForce, double maxSpeed, T shape) {
        this(position, new Point2D(0, 0), maxForce, maxSpeed, shape);
    }

    public MovableEntity(Point2D position, Point2D velocity, double maxForce, double maxSpeed, T shape) {
        super(position, shape);

        this.velocity = velocity;
        // By default, the entity has no acceleration
        this.acceleration = new Point2D(0, 0);

        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;

        // Friction has to be set externally. This is the case since friction might have to be updated/changed
        // each update.
        this.friction = 0.0;
    }

    /* Takes change in time in nanoseconds since last update */
    @Override
    public void update(double delta, double timeStep) {
        /* Limit the acceleration to maxForce, and multiply with the delta time value */
        setAcceleration(acceleration.multiply(delta * timeStep));

        /* Add acceleration to velocity */
        setVelocity(velocity.add(acceleration));

        /* Add friction to velocity, and limit the velocity to max speed */
        setVelocity(Utils.limit(velocity.add(velocity.multiply(-friction * delta * timeStep)), maxSpeed));

        /* Limit minimum velocity to minSpeed */
        velocity = Utils.lowerLimit(velocity, minSpeed);

        /* Add velocity to position */
        move(velocity.multiply(delta * timeStep));

        /* Reset acceleration */
        setAcceleration(new Point2D(0, 0));
    }

    @Override
    public void move(Point2D offset) {
        position = position.add(offset);
    }

    @Override
    public void addForce(Point2D force) {
        acceleration = acceleration.add(Utils.limit(force, maxForce));
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
    public double getMaxForce() {
        return maxForce;
    }

    @Override
    public double getMaxSpeed() {
        return maxSpeed;
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

    @Override
    public void setFriction(double friction) {
        this.friction = friction;
    }

    @Override
    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }
}
