package game.model.entity;

import game.model.IPositionable;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public interface IEntity extends IPositionable {
    double getWidth();
    double getHeight();
    Shape getShape();
    boolean checkCollision(IEntity entity);

    void update(double delta);
}
