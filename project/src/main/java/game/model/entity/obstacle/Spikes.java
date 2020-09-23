package game.model.entity.obstacle;

import game.model.entity.Entity;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;

public class Spikes extends Entity<Rectangle> implements IObstacle<Rectangle> {

    private final double damage;

    public Spikes(Point2D position, double width, double height, double damage) {
        super(position, new Rectangle(width, height,0));
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    @Override
    public void update(double delta, double timestep) {

    }
}

