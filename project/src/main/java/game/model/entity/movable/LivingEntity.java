package game.model.entity.movable;


import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

//Class from which all living entities inherits.
//Has hit points which upon reaching zero will cause the death of the entity.
public abstract class LivingEntity<T extends IShape2D> extends MovableEntity<T> implements ILiving {
    // The number of hit points for this living entity
    private int hitPoints;
    // True if the entity is invulnerable. If an entity is invulnerable, it cannot be killed by another entity
    private boolean isInvulnerable;
    // The strength (or damage) of the entity.
    private int strength;

    public LivingEntity(Point2D position, double maxForce, double maxSpeed, int hitPoints, T shape, int strength) {
        super(position, maxForce, maxSpeed, shape);
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.isInvulnerable = false;
    }

    @Override
    public int getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public int getStrength() { return strength; }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public boolean isAlive() {
        return this.hitPoints > 0;
    }
    
    @Override
    public boolean isInvulnerable() {
        return isInvulnerable;
    }

    @Override
    public void setIsInvulnerable(boolean isInvulnerable) {
        this.isInvulnerable = isInvulnerable;
    }
}