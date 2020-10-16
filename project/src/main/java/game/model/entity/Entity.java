package game.model.entity;

import game.model.shape2d.IShape2D;
import game.util.Shapes;
import javafx.geometry.Point2D;

// Abstract entity implementation.
public abstract class Entity<T extends IShape2D> implements IEntity<T> {
    // The position of the entity
    protected Point2D position;

    // The shape of the entity
    private final T shape;

    public Entity(Point2D position, T shape) {
        this.position = position;
        this.shape = shape;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public T getShape(){
        return shape;
    }

    // Checks collision with another entity. A helper method from the Shapes helper class is used for this purpose.
    @Override
    public <V extends IShape2D> boolean checkCollision(IEntity<V> entity) {
        // This method is used to simplify collision checking. Since shapes have no position,
        // a test for collision between two entities would require manually filling in correct positions for both
        // involved shapes. This simplifies the method calls.
        return Shapes.testCollision(shape, position, entity.getShape(), entity.getPosition());
    }

}
