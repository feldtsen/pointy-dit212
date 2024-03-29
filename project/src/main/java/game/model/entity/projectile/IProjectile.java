/*
 * Authors: Mattias Oom, Simon Genne, Anton Hildingsson, Erik Magnusson
 *
 * Interface for all projectiles in the game. Projectiles can be destroyed,
 * which typically happens when they hit another entity or move out of bounds. 
 */

package game.model.entity.projectile;

import game.model.entity.movable.IMovable;
import game.model.entity.IStrength;
import game.model.shape2d.IShape2D;

public interface IProjectile<T extends IShape2D> extends IStrength, IMovable<T> {
    // Returns true if the projectile is destroyed.
    // A projectile is typically destroyed when hitting an entity or moving out of bounds
    boolean isDestroyed();

    // Sets the projectile to be destroyed
    void setDestroyed();
}
