package game.model.entity.obstacle;

import game.model.entity.Entity;
import game.model.entity.IEntity;
import javafx.geometry.Point2D;

public class Wall extends Entity implements IObstacle {
    private double width;
    private double height;

    public Wall(Point2D position, double width, double height) {
        super(position, width); //TODO: Constructor of Entity takes radius as second arg. Radius of wall?
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean checkCollision(IEntity entity) {
        /* Size and coordinates of the entity to check for collision. */
        double entityWidth = entity.getWidth();
        double entityHeight = entity.getHeight();
        double entityX = entity.getPosition().getX();
        double entityY = entity.getPosition().getY();

        /* Coordinates of wall. */
        double wallX = super.getPosition().getX();
        double wallY = super.getPosition().getY();

        /* Booleans indicating if the entity overlaps with the wall in x- and y-axis. */
        boolean xOverlap = false;
        boolean yOverlap = false;

        if (entityX < wallX + width && entityX + entityWidth > wallX) {
            xOverlap = true;
        }

        if (entityY < wallY + height && entityY + entityHeight > wallY) {
            yOverlap = true;
        }

        /* True if there is an overlap both in x- and y-axis. */
        return xOverlap && yOverlap;
    }

    @Override
    public boolean handleCollision(IEntity entity) {
        return false;
    }

    @Override
    public void update(double delta) {
        //TODO: How should a wall update?
    }
}
