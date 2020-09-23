package game.model.entity.obstacle;

import game.model.entity.movable.MovableEntity;
import game.model.shape2d.Rectangle;
import game.util.Utils;
import javafx.geometry.Point2D;

public class MovingWall extends MovableEntity<Rectangle> implements IObstacle {

    private final Point2D startPosition;
    private final Point2D endPosition;


    public MovingWall(Point2D startPosition, Point2D endPosition, double maxSpeed, double maxForce, double width, double height) {
        super(startPosition.midpoint(endPosition), Utils.limit(endPosition.subtract(startPosition), maxSpeed), maxSpeed, maxForce, new Rectangle(width, height,0));
        this.startPosition = startPosition;
        this.endPosition = endPosition;


    }

    @Override
    public void update(double delta, double timestep) {
        //TODO: Fix so that obstacle can start at either extreme position without changing direction of velocity
        if (startPosition.subtract(getPosition()).getX() >= 0 || endPosition.subtract(getPosition()).getX() <= 0) {
            setVelocity(new Point2D(getVelocity().getX() * -1, getVelocity().getY()));
        }
        if (startPosition.subtract(getPosition()).getY() >= 0 || endPosition.subtract(getPosition()).getY() <= 0) {
            setVelocity(new Point2D(getVelocity().getX(), getVelocity().getY() * -1));
        }
        super.update(delta, timestep);
    }
}
