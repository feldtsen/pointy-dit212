package game.model.entity.movable;


import game.model.ILiving;
import game.model.entity.IStrength;
import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

public abstract class LivingEntity<T extends IShape2D> extends MovableEntity<T> implements ILiving {

    private int hitPoints;
    private final double strength;

    public LivingEntity(Point2D position, double maxForce, double maxSpeed, int hitPoints, T shape) {
        this(position, maxForce, maxSpeed, hitPoints, shape, 0);
    }
    public LivingEntity(Point2D position, double maxForce, double maxSpeed, int hitPoints, T shape, double strength) {
        super(position, maxForce, maxSpeed, shape);
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    @Override
    public int getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public double getStrength() { return strength; }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public boolean isAlive() {
        return this.hitPoints > 0;
    }
}