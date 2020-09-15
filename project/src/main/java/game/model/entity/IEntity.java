package game.model.entity;

import game.model.IPositionable;
import javafx.geometry.Point2D;

public interface IEntity extends IPositionable {
    double getWidth();
    double getHeight();
    boolean checkCollision(IEntity entity);
    boolean handleCollision(IEntity entity);

    void update(double delta);
}
