package game.model.entity;

import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

public abstract class Entity<T extends IShape2D> implements IEntity<T> {
    //TODO: protected currently, is this a bad idea maybe hmmmm?
    protected Point2D position;
    private T shape;

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
    public boolean checkCollision(IEntity<?> entity) {
        return false;
    }

    @Override
    public abstract void update(double delta);
}
