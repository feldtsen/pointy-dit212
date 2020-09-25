package game.model.entity.projectile;

import game.model.entity.IEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.model.shape2d.IShape2D;
import javafx.geometry.Point2D;

public class Bullet extends Projectile<ICircle> {

    public Bullet(Point2D position, double radius, double maxForce, double maxSpeed, int strength, Point2D velocity) {
        super(position, maxForce, maxSpeed, strength, velocity, new Circle(radius));
    }


}

