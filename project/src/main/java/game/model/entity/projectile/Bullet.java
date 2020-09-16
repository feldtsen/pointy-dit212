package game.model.entity.projectile;

import game.model.entity.IEntity;
import javafx.geometry.Point2D;

public class Bullet extends Projectile{

    public Bullet(Point2D position, double radius, double maxForce, double maxSpeed, int damage, Point2D velocity) {
        super(position, radius, maxForce, maxSpeed, damage, velocity);
    }

    @Override
    public boolean handleCollision(IEntity entity) {
        return false;
    }
}