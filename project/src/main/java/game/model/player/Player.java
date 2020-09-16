package game.model.player;

import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import game.model.entity.movable.MovableEntity;
import javafx.geometry.Point2D;

public class Player extends LivingEntity implements IPlayer {
    private final static Point2D LEFT  = new Point2D(-1, 0);
    private final static Point2D RIGHT = new Point2D(1, 0);
    private final static Point2D UP    = new Point2D(0, -1);
    private final static Point2D DOWN  = new Point2D(0, 1);
    private double acceleration;

    public Player(Point2D position, double radius, double maxForce, double maxSpeed, double acceleration) {
        super(position, radius, maxForce, maxSpeed, 1);
        this.acceleration = acceleration;
    }

    @Override
    public boolean activateAbility() {
        return false;
    }

    @Override
    public void moveUp() {
        Point2D force = UP.multiply(acceleration);
        addForce(force);
    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public boolean handleCollision(IEntity entity) {
        return false;
    }
}
