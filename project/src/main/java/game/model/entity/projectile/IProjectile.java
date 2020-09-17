package game.model.entity.projectile;

import game.model.shape2d.IShape2D;

public interface IProjectile<T extends IShape2D> {
    int getDamage();
    boolean isDestroyed();
    void setDestroyed();
}
