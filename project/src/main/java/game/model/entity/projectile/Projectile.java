package game.model.entity.projectile;

import game.model.entity.movable.MovableEntity;
import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

// Abstract helper implementation of projectiles.
public abstract class Projectile<T extends IShape2D> extends MovableEntity<T> implements IProjectile<T> {
    private final int strength;
    private boolean isDestroyed;

    public Projectile(Point2D position, double maxForce, double maxSpeed, int strength, Point2D velocity, T shape) {
        super(position, velocity, maxForce, maxSpeed, shape);
        this.strength = strength;
        this.isDestroyed = false;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void setDestroyed() {
        isDestroyed = true;
    }

}
