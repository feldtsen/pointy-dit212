package game.model.entity.projectile;

import game.model.IMovable;
import game.model.entity.IEntity;
import game.model.entity.IStrength;
import game.model.shape2d.IShape2D;

// Interface for all projectiles in the game.
public interface IProjectile<T extends IShape2D> extends IStrength, IMovable<T> {
    boolean isDestroyed();
    void setDestroyed();
}
