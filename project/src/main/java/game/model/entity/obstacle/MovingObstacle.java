package game.model.entity.obstacle;

import game.model.entity.movable.MovableEntity;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;

public class MovingObstacle extends MovableEntity<Rectangle> implements IObstacle<Rectangle> {

    private final Point2D startPosition;
    private final Point2D endPosition;

    public MovingObstacle(Point2D startPosition, Point2D endPosition, Point2D velocity, double maxSpeed, double maxForce, double width, double height) {
        super(startPosition, velocity, maxSpeed, maxForce, new Rectangle(width, height,0));

        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }



}
