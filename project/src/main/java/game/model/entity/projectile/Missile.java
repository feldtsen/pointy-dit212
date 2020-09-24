package game.model.entity.projectile;

import game.model.entity.IEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

public class Missile extends Projectile<ICircle> {

    private double steering; //How fast a missile turns towards the player
    private IEntity<?> target;

    public Missile(Point2D position, double radius, double maxForce, double maxSpeed, double strength, Point2D velocity, IEntity<?> target) {
        super(position, maxForce, maxSpeed, strength, velocity, new Circle(radius));
        this.target = target;
    }


    public boolean setTarget(IEntity<?> target){
        this.target = target;
        return true;    //TODO: Temporary, Change when invalid targets exist
    }

    public IEntity<?> getTarget(){
        return target;
    }


    //TODO: Use this to implement missile homing
    @Override
    public void update(double delta, double timeStep) {
        super.update(delta, timeStep);
    }
}
