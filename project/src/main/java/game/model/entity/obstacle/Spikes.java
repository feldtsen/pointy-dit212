/*
 * Authors: Erik Magnusson, Anton Hildingsson, Simon Genne
 *
 * Spike obstacle which entities risk injury or death if colliding with.
 * Strength marks damage done and is implemented through interface IStrength.
 * However, spikes cannot be killed by entities with higher strength as is normally the case.
 */

package game.model.entity.obstacle;

import game.model.entity.Entity;
import game.model.entity.IStrength;
import game.model.entity.movable.ILiving;
import game.model.entity.movable.IMovable;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;

public class Spikes extends Entity<Rectangle> implements IObstacle, IStrength {
    // The strength of the spikes. This is the amount of damage they will do to an living entity
    private int strength;

    public Spikes(Point2D position, double width, double height, int strength) {
        super(position, new Rectangle(width, height,0));
        this.strength = strength;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public void update(double delta, double timeStep) {
        // Do nothing
    }

    @Override
    public void handleCollision(Point2D minimumTranslationVector, IMovable entity) {
        ILiving livingEntity = (ILiving) entity;
        if (getStrength() > livingEntity.getStrength() && !livingEntity.isInvulnerable()) {
            livingEntity.setHitPoints(0);
        }
    }
}

