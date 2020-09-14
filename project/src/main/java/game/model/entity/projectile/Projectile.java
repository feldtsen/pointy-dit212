package game.model.entity.projectile;

import game.model.entity.movable.MovableEntity;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light;

public abstract class Projectile extends MovableEntity implements IProjectile {
    private final int damage;
    private boolean isDestroyed;


    public Projectile(Point2D position, double radius, double maxForce, double maxSpeed, int damage, Point2D velocity) {
        super(position, velocity, radius, maxForce, maxSpeed);
        this.damage = damage;
        this.isDestroyed = false;
    }
}
