package game.model.entity.enemy;

import game.model.ILiving;
import game.model.IMovable;
import game.model.behavior.IAbilityBehaviour;
import game.model.behavior.movement.IMovementBehaviour;

public interface IEnemy extends ILiving, IMovable {
    void setMovementBehaviour(IMovementBehaviour movementBehaviour);
    void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour);
}