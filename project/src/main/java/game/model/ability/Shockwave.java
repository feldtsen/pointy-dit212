package game.model.ability;

import game.model.entity.movable.IMovable;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.level.ILevel;
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Shockwave extends Ability{
    private final double radius;
    private final double force;
    private final double duration;

    public Shockwave(long cooldown, double radius, double force, double duration) {
        super(cooldown);
        this.radius = radius;
        this.force = force;
        this.duration = duration;
    }

    public IAbilityAction createAction(IEntity<?> user, IEntity<?> target){
        // Anonymous implementation of an ability action is created on each use.
        return new IAbilityAction() {
            @Override
            public double getDuration() {
                return duration;
            }

            @Override
            public void apply(ILevel level, double timePassed) {
                // Collect all entities which can be affected by the ability in one list of movables.
                List<IMovable<?>> entities = new ArrayList<>(level.getEnemies());
                entities.add(level.getPlayer());

                // Iterate over list to check which entities are to be affected
                for(IMovable<?> target : entities){
                    // Do not affect user of ability
                    if (user == target) continue;

                    // Create vector pointing from user to target entity
                    Point2D v = target.getPosition().subtract(user.getPosition());

                    // Check if the target entity is within radius
                    if (Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2) <= Math.pow(radius, 2)) {
                        // Calculate the distance between the user and the target
                        double distance = v.magnitude();

                        // Calculate "power", signifying how effective the ability will be.
                        // Power will approach 1 as the target's distance to the user approaches 0.
                        double power = 1 - distance/radius;

                        // Create an acceleration vector which will be applied to the target.
                        Point2D acceleration = Utils.setMagnitude(v, power * force);
                        target.setAcceleration(target.getAcceleration().add(acceleration));
                    }
                }
            }
        };
    }
}
