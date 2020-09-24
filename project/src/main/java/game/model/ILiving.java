package game.model;

import game.model.entity.IStrength;

public interface ILiving extends IStrength {
    int getHitPoints();
    void setHitPoints(int hitPoints);
    boolean isAlive();
}
