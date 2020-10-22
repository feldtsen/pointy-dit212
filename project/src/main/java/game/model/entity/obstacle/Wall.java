/*
 * Authors: Simon Genne, Anton Hildingsson, Erik Magnusson, Mattias Oom
 *
 * Basic obstacle which entities can collide with and use for cover.
 */

package game.model.entity.obstacle;
import game.model.entity.Entity;
import game.model.entity.movable.IMovable;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;

public class Wall extends Entity<Rectangle> implements IObstacle{

    // A wall is always a rectangle
    public Wall(Point2D position, double width, double height) {
        super(position, new Rectangle(width, height,0));
    }

    @Override
    public void update(double delta, double timeStep) {
        // Do nothing
    }

    @Override
    // Takes a minimum translation vector (vector describing the minimum length and direction that the entity should be
    // moved to undo the direction) and the entity that the collision has occurred with, and undoes the collision.
    // The velocity of the entity in the direction that the collision has occurred in, will be set to 0.
    public void handleCollision(Point2D minimumTranslationVector, IMovable entity) {

        // Undo the collision by moving the entity according to the given translation vector.
        entity.move(minimumTranslationVector);

        // Get entity's velocity in the direction of the collision (project entity's velocity onto the mtv).
        Point2D collisionVelocity = minimumTranslationVector.normalize().multiply(minimumTranslationVector.
                                                dotProduct(entity.getVelocity())/minimumTranslationVector.magnitude());

        // Set entity's velocity in direction of collision to 0.
        entity.setVelocity(entity.getVelocity().subtract(collisionVelocity));
    }
}
