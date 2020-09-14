package game.model.entity;


import game.model.ILiving;
import javafx.geometry.Point2D;

public abstract class LivingEntity extends MovableEntity implements ILiving {

    private int hitPoints;

    public LivingEntity(Point2D position, double radius, double maxForce, double maxSpeed, int hitPoints) {
        super(position, radius, maxForce, maxSpeed);
        this.hitPoints = hitPoints;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public boolean isAlive() {
        return this.hitPoints > 0;
    }
}