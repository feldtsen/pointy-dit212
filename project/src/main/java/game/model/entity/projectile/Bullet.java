/*
 * Authors: Mattias Oom, Simon Genne, Anton Hildingsson, Erik Magnusson
 *
 * Simple bullet implementation for projectile. A simple circle shape which moves in a single direction.
 * Should hurt living entities on hit.
 */

package game.model.entity.projectile;

import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import javafx.geometry.Point2D;

public class Bullet extends Projectile<ICircle> {
    public Bullet(Point2D position, double radius, double maxForce, double maxSpeed, int strength, Point2D velocity) {
        super(position, maxForce, maxSpeed, strength, velocity, new Circle(radius));
    }
}

