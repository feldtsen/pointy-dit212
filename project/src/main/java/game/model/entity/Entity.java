package game.model.entity;

import game.model.shape2d.IShape2D;
import game.model.shape2d.Shapes;
import javafx.geometry.Point2D;


public abstract class Entity<T extends IShape2D> implements IEntity<T> {
    //TODO: avoid protected variable possibly?
    protected Point2D position;
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

    @Override
    public <V extends IShape2D> boolean checkCollision(IEntity<V> entity) {
        return Shapes.testCollision(shape, position, entity.getShape(), entity.getPosition());
    }

}
