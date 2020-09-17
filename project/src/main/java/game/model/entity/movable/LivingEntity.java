package game.model.entity.movable;


import game.model.ILiving;
import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

public abstract class LivingEntity extends MovableEntity implements ILiving {

    private int hitPoints;

    public LivingEntity(Point2D position, double maxForce, double maxSpeed, int hitPoints, IShape2D shape) {
        super(position, maxForce, maxSpeed, shape);
        this.hitPoints = hitPoints;
    }

    @Override
    public int getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public boolean isAlive() {
        return this.hitPoints > 0;
    }
}