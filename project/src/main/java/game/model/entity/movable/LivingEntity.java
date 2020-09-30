package game.model.entity.movable;


import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

//Class from which all living entities inherits.
//Has hit points which upon reaching zero will cause the death of the entity.
public abstract class LivingEntity<T extends IShape2D> extends MovableEntity<T> implements ILiving {

    private int hitPoints;
    private final int strength;

    public LivingEntity(Point2D position, double maxForce, double maxSpeed, int hitPoints, T shape, int strength) {
        super(position, maxForce, maxSpeed, shape);
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    @Override
    public int getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public int getStrength() { return strength; }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public boolean isAlive() {
        return this.hitPoints > 0;
    }
}