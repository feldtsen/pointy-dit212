package game.model.entity.movable;

import game.model.IMovable;
import game.model.entity.Entity;
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
    public void update(long delta) {
        /* Calculate time increment in seconds */
        double nano = 0.000000001;
        double deltaFactor = (double)delta * nano; //TODO: change delta to be time increment instead?

        /* Limit the acceleration to maxForce, and multiply with the delta time value */
        setAcceleration(limit(acceleration, maxForce).multiply(deltaFactor));

        /* Add acceleration to velocity, and limit the velocity to max speed */
        setVelocity(limit(velocity.add(acceleration), maxSpeed));

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

    /* Helper methods */
    //TODO: move to util class?
    public static Point2D limit(Point2D vector, double maxMagnitude) { // Public only for testing purposes...
        if(vector == null || maxMagnitude < 0) throw new IllegalArgumentException();

        /* Checks for the square of the magnitude instead of the magnitude itself, to avoid having to use
         * the sqrt operator.
         */
        if(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) > maxMagnitude * maxMagnitude) {
            double magnitude = vector.magnitude();
            double factor = maxMagnitude / magnitude;
            return vector.multiply(factor);
        } else {
            return vector;
        }
    }
}
