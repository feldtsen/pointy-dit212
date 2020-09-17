package game.model.entity;

import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

public abstract class Entity implements IEntity {
    //TODO: protected currently, is this a bad idea maybe hmmmm?
    protected Point2D position;
    private double radius;
    private IShape2D shape;

    public Entity(Point2D position, double radius) {
        this.position = position;
        this.radius = radius;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public double getWidth() {
        return radius;
    }

    @Override
    public double getHeight() {
        return radius;
    }

    @Override
    public IShape2D getShape(){
        return shape;
    }

    @Override
    public boolean checkCollision(IEntity entity) {
        /* Minimum distance between entities before collision occurs */
        double minDist = this.radius + entity.getWidth(); // getWidth() == getHeight() == radius

        /* Difference in positions between entities. Used to calculate the square of the distance between the entities. */
        Point2D diff = this.position.subtract(entity.getPosition());

        /* Check collision with the square of the distance between the entities.
         * This avoids the use of the square root operator, which is an expensive operation.
         */
        return diff.getX() * diff.getX() + diff.getY() * diff.getY() <= minDist * minDist;
    }

    @Override
    public abstract void update(double delta);
}
