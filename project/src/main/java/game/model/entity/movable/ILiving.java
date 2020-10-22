/*
 * Authors: Mattias Oom, Erik Magnusson, Anton Hildingsson
 *
 * Interface for any living object in the game.
 * All living entities has a strength, which signifies how much 
 * damage it could potentially do to another living entity.
 */

package game.model.entity.movable;

import game.model.entity.IStrength;

public interface ILiving extends IStrength {
    // The number of hit points for this object
    int getHitPoints();
    void setHitPoints(int hitPoints);

    // Returns true if hitPoints is greater than 0.
    boolean isAlive();

    // Returns true if the entity cannot die
    boolean isInvulnerable();

    // Set invulnerable state
    void setIsInvulnerable(boolean invulnerable);
}
