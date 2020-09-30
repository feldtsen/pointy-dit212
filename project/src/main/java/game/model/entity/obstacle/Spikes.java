package game.model.entity.obstacle;

import game.model.entity.Entity;
import game.model.entity.IStrength;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;

//Spike obstacle which entities risk injury or death if colliding with.
//Strength marks damage done and is implemented through interface IStrenght.
//However, spikes cannot be killed by entities with higher strength as is normally the case.
public class Spikes extends Entity<Rectangle> implements IObstacle, IStrength {
    private final int strength;

    public Spikes(Point2D position, double width, double height, int strength) {
        super(position, new Rectangle(width, height,0));
        this.strength = strength;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void update(double delta, double timeStep) {
        // Do nothing
    }
}

