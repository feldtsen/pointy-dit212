package game.model.entity.movable;

import game.model.entity.IStrength;

// Interface for any living object in the game.
public interface ILiving extends IStrength {
    int getHitPoints();
    void setHitPoints(int hitPoints);

    // Returns true if hitPoints is greater than 0.
    boolean isAlive();
}
