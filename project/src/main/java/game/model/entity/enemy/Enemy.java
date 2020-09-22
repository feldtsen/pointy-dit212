package game.model.entity.enemy;

import game.model.behavior.ability.IAbilityBehaviour;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import javafx.geometry.Point2D;

public class Enemy extends LivingEntity<ICircle> implements IEnemy<ICircle> {


    private final int strength;
    private IEntity<?> target;
    private IMovementBehaviour movementBehaviour;
    private IAbilityBehaviour abilityBehaviour;

    public Enemy(Point2D position, double radius, double maxForce, double maxSpeed, int hitPoints, IAbilityBehaviour abilityBehaviour, IMovementBehaviour movementBehaviour, IEntity<?> target){
        super(position, maxForce, maxSpeed, hitPoints, new Circle(radius));
        this.abilityBehaviour = abilityBehaviour;
        this.movementBehaviour = movementBehaviour;
        this.target = target;
        this.strength = 1;
    }

    public Enemy(Point2D position, double radius, double maxForce, double maxSpeed, int hitPoints, IAbilityBehaviour abilityBehaviour, IMovementBehaviour movementBehaviour, IEntity<?> target, int strength) {
        super(position, maxForce, maxSpeed, hitPoints, new Circle(radius));
        this.abilityBehaviour = abilityBehaviour;
        this.movementBehaviour = movementBehaviour;
        this.target = target;
        this.strength = strength;
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
        if(target == this) {
            return false;
        }
        this.target = target;
        return true;
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        movementBehaviour.apply(this, target);
    }
}
