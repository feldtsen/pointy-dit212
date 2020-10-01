package game.model.entity.enemy;

import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.IAbilityBehaviour;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import javafx.geometry.Point2D;

public class Enemy extends LivingEntity<ICircle> implements IEnemy {
    private IEntity<?> target; // Target may be null, if the enemy has no particular goal
    private IMovementBehaviour movementBehaviour;
    private IAbilityBehaviour abilityBehaviour;

    public Enemy(Point2D position, double radius, double maxForce, double maxSpeed, int hitPoints, int strength, IAbilityBehaviour abilityBehaviour, IMovementBehaviour movementBehaviour, IEntity<?> target){
        this(position, radius, maxForce, maxSpeed, hitPoints, abilityBehaviour, movementBehaviour, target, strength);
    }

    public Enemy(Point2D position, double radius, double maxForce, double maxSpeed, int hitPoints, IAbilityBehaviour abilityBehaviour, IMovementBehaviour movementBehaviour, IEntity<?> target, int strength) {
        // All enemies have a circle shape
        super(position, maxForce, maxSpeed, hitPoints, new Circle(radius),strength);
        this.abilityBehaviour = abilityBehaviour;
        this.movementBehaviour = movementBehaviour;
        this.target = target;
    }

    @Override
    public void setMovementBehaviour(IMovementBehaviour movementBehaviour) {
        this.movementBehaviour = movementBehaviour;
    }

    @Override
    public void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour) {
        this.abilityBehaviour = abilityBehaviour;
    }

    @Override
    public boolean setTarget(IEntity<?> target) {
        // If the target is the enemy itself, return false
        if(target == this) {
            return false;
        }
        this.target = target;
        return true;
    }

    @Override
    public IAbilityAction applyAbility() {
        // Do not apply ability if there is no abilityBehavior
        if(abilityBehaviour == null) return null; // Game will disregard an ability action if null is returned
        return abilityBehaviour.apply(this, target);
    }

    @Override
    public void update(double delta, double timeStep) {
        // And apply movement behavior
        movementBehaviour.apply(this, target);
        // Call movable entity update method
        super.update(delta, timeStep);
    }

}
