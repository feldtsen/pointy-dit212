package game.model.player;

import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import javafx.geometry.Point2D;

public class Player extends LivingEntity implements IPlayer {
    private final static Point2D LEFT  = new Point2D(-1, 0);
    private final static Point2D RIGHT = new Point2D(1, 0);
    private final static Point2D UP    = new Point2D(0, -1);
    private final static Point2D DOWN  = new Point2D(0, 1);

    public Player(Point2D position, double radius, double responsiveness, double maxSpeed) {
        super(position, radius, responsiveness, maxSpeed, 1);
    }

    @Override
    public boolean activateAbility() {
        return false;
    }

    @Override
    public void moveUp() {
        moveDirection(UP);
    }

    @Override
    public void moveLeft() {
        moveDirection(LEFT);
    }

    @Override
    public void moveDown() {
        moveDirection(DOWN);
    }

    @Override
    public void moveRight() {
        moveDirection(RIGHT);
    }

    private void moveDirection(Point2D direction) {
       addForce(direction.multiply(getMaxForce()));
    }
}
