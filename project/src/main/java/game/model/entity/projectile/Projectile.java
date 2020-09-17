package game.model.entity.projectile;

import game.model.entity.movable.MovableEntity;
import game.model.shape2d.Circle;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light;

public abstract class Projectile extends MovableEntity implements IProjectile {
    private final int damage;
    private boolean isDestroyed;


    public Projectile(Point2D position, double radius, double maxForce, double maxSpeed, int damage, Point2D velocity) {
        super(position, velocity, maxForce, maxSpeed, new Circle(radius));
        this.damage = damage;
        this.isDestroyed = false;
    }

    @Override
    public int getDamage() {
        return damage;
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
