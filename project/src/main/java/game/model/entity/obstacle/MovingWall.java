package game.model.entity.obstacle;

import game.model.entity.movable.MovableEntity;
import game.model.shape2d.Rectangle;
import game.util.Utils;
import javafx.geometry.Point2D;

public class MovingWall extends MovableEntity<Rectangle> implements IObstacle<Rectangle> {

    private final Point2D startPosition;
    private final Point2D endPosition;
    private Point2D position;

    public MovingWall(Point2D startPosition, Point2D endPosition, Point2D velocity, double maxSpeed, double maxForce, double width, double height) {
        super(startPosition, velocity, maxSpeed, maxForce, new Rectangle(width, height,0));

        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.position = startPosition;
    }

    @Override
    public void update(double delta, double timestep) {
        //TODO
        //Implement proper movement function for moving obstacles
        super.update(delta, timestep);
    }
}
