package game.model.entity.obstacle;

import game.model.entity.movable.MovableEntity;
import game.model.shape2d.Rectangle;
import game.util.Utils;
import javafx.geometry.Point2D;


//An obstacle which moves in a fixed trajectory around the level.
//The obstacle will move from the starting position to the end position, switching directions thereafter.
//Speed vector is calulated from the distance between the two positions' x and y coordinates.
//Velocity is normalized to maxSpeed using Util limit.
public class MovingWall extends MovableEntity<Rectangle> implements IObstacle {
    // The start position of the moving wall. The moving wall will then move towards the end position, and back again
    private final Point2D startPosition;
    private final Point2D endPosition;

    //Obstacle initiated at the midpoint between starting and end positions.
    public MovingWall(Point2D startPosition, Point2D endPosition, double maxSpeed, double maxForce, double width, double height) {
        super(startPosition, Utils.limit(endPosition.subtract(startPosition), maxSpeed), maxSpeed, maxForce, new Rectangle(width, height,0));
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    //Changes direction of travel when reaching either starting position or end position.
    @Override
    public void update(double delta, double timeStep) {
        // Reaching starting position x, left side of screen
        if (startPosition.subtract(getPosition()).getX() > 0)  {
            setVelocity(new Point2D(Math.abs(getVelocity().getX() * -1), getVelocity().getY()));
        }
        // Reaching end position x, right side of screen
        else if (endPosition.subtract(getPosition()).getX() < 0) {
            setVelocity(new Point2D(-Math.abs(getVelocity().getX() * -1), getVelocity().getY()));
        }
        // Reaching starting position y,top of screen,
        if (startPosition.subtract(getPosition()).getY() > 0) {
            setVelocity(new Point2D(getVelocity().getX(), Math.abs(getVelocity().getY() * -1)));
        }
        // Reaching end position y, bottom of screen
        else if (endPosition.subtract(getPosition()).getY() < 0) {
            setVelocity(new Point2D(getVelocity().getX(), -Math.abs(getVelocity().getY() * -1)));
        }
        // Update
        super.update(delta, timeStep);
    }
}
