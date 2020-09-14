package game.model.entity;

import game.model.IMovable;
import javafx.geometry.Point2D;

public abstract class MovableEntity extends Entity implements IMovable {
    private double maxForce;
    private double maxSpeed;

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

    @Override
    public void update(long delta) {
        //TODO: take delta into account!
        setAcceleration(limit(acceleration, maxForce));

        setVelocity(limit(velocity.add(acceleration), maxSpeed));

        setPosition(position.add(velocity));

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
    public static Point2D limit(Point2D vector, double maxMagnitude) { // Public only for testing purposes...
        /* Checks for the square of the magnitude instead of the magnitude itself, to avoid having to use
         * the sqrt operator.
         */
        if(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) < maxMagnitude * maxMagnitude) {
            double magnitude = vector.magnitude();
            double factor = maxMagnitude / magnitude;
            return vector.multiply(factor);
        } else {
            return vector;
        }
    }
}
