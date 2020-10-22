/*
 * Authors: Anton Hildingsson, Erik Magnusson, Simon Genne
 * Entities with strength are able to kill other entities on touch.
 * If the strength is equal, both entities survive.
 * Potential hit points are not adjusted.
 */

package game.model.entity;

public interface IStrength {
    int getStrength();

    void setStrength(int strength);

    // Checks if an entity is stronger than another entity
    default boolean isStrongerThan(IStrength other) {
        return this.getStrength() > other.getStrength();
    }
}
