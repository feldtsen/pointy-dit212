package game.model.entity.obstacle;

import game.model.entity.Entity;
import game.model.entity.IKillOnTouch;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;

public class Spikes extends Entity<Rectangle> implements IObstacle, IKillOnTouch {

    private final double strength;

    public Spikes(Point2D position, double width, double height, double strength) {
        super(position, new Rectangle(width, height,0));
        this.strength = strength;
    }

    @Override
    public double getStrength() {
        return strength;
    }

    @Override
    public void update(double delta, double timestep) {

    }
}

