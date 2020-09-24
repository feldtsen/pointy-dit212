package game.model.entity.projectile;

import game.model.entity.IStrength;
import game.model.shape2d.IShape2D;

public interface IProjectile<T extends IShape2D> extends IStrength {
    boolean isDestroyed();
    void setDestroyed();
}
