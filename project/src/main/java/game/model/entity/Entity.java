package game.model.entity;

import game.model.IMovable;
import game.model.IPositionable;
import javafx.geometry.Point2D;

public abstract class Entity implements IPositionable {
    private Point2D position;
    private double size;

    public Entity(Point2D position, double size) {
        this.position = position;
        this.size = size;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public double getSize() {
        return size;
    }

    public boolean checkCollision(Entity entity) {
        /* Minimum distance between entities before collision occurs */
        double minDist = this.size + entity.getSize();

        /* Difference in positions between entities. Used to calculate the square of the distance between the entities. */
        Point2D diff = this.position.subtract(entity.getPosition());

        /* Check collision with the square of the distance between the entities.
         * This avoids the use of the square root operator, which is an expensive operation.
         */
        return diff.getX() * diff.getX() + diff.getY() * diff.getY() < minDist * minDist;
    }

    public abstract boolean handleCollision(Entity entity);
}
