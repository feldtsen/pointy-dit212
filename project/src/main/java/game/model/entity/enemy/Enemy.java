package game.model.entity.enemy;

import game.model.ability.IAbilityBehaviour;
import game.model.behavior.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import javafx.geometry.Point2D;

public class Enemy extends LivingEntity implements IEnemy {

    private IMovementBehaviour movementBehaviour;
    private IAbilityBehaviour abilityBehaviour;


    public Enemy(Point2D position, double radius, double maxForce, double maxSpeed, int hitPoints, IAbilityBehaviour abilityBehaviour, IMovementBehaviour movementBehaviour) {
        super(position, radius, maxForce, maxSpeed, hitPoints);
        this.abilityBehaviour = abilityBehaviour;
        this.movementBehaviour = movementBehaviour;
    }

    @Override
    public boolean handleCollision(IEntity entity) {
        return false;
    }

    @Override
    public boolean attack() {
        return false;
    }

    @Override
    public void setMovementBehaviour(IMovementBehaviour movementBehaviour) {

    }

    @Override
    public void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour) {

    }
}
