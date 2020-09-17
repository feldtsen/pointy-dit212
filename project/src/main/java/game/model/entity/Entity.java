package game.model.entity;

import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

public abstract class Entity implements IEntity {
    //TODO: protected currently, is this a bad idea maybe hmmmm?
    protected Point2D position;
    private IShape2D shape;

    public Entity(Point2D position, IShape2D shape) {
        this.position = position;
        this.shape = shape;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public IShape2D getShape(){
        return shape;
    }

    @Override
    public boolean checkCollision(IEntity entity) {
        return false;
    }

    @Override
    public abstract void update(double delta);
}
