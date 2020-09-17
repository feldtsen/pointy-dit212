package game.model.entity;

import game.model.IPositionable;
import game.model.shape2d.IShape2D;

public interface IEntity extends IPositionable {
    double getWidth();
    double getHeight();
    boolean checkCollision(IEntity entity);
    IShape2D getShape();
    void update(double delta);
}
