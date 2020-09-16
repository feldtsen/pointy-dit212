package game.model.entity.projectile;

import game.model.entity.IEntity;
import javafx.geometry.Point2D;

public class Missile extends Projectile{

    private IEntity target;

    public Missile(Point2D position, double radius, double maxForce, double maxSpeed, int damage, Point2D velocity, IEntity target) {
        super(position, radius, maxForce, maxSpeed, damage, velocity);
        this.target = target;
    }


    public boolean setTarget(IEntity target){
        this.target = target;
        return true;    //TODO: Temporary, Change when invalid targets exist
    }

    public IEntity getTarget(){
        return target;
    }

    //Todo: Remove, collision should be handle by higher level
    @Override
    public boolean handleCollision(IEntity entity) {
        return false;
    }

    //TODO: Use this to implement missile homing
    @Override
    public void update(double delta) {
        super.update(delta);
    }
}
