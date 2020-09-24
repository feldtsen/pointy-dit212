package game.model.entity.projectile;

import game.model.entity.movable.MovableEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light;

public abstract class Projectile<T extends IShape2D> extends MovableEntity<T> implements IProjectile<T> {
    private final double strength;
    private boolean isDestroyed;


    public Projectile(Point2D position, double maxForce, double maxSpeed, double strength, Point2D velocity, T shape) {
        super(position, velocity, maxForce, maxSpeed, shape);
        this.strength = strength;
        this.isDestroyed = false;
    }

    @Override
    public double getStrength() {
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
