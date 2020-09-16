package game.model.entity;

import game.model.ILiving;
import game.model.IMovable;
import game.model.ability.IAbilityBehaviour;
import game.model.behavior.IMovementBehaviour;

public interface IEnemy extends ILiving, IMovable {
    boolean attack();
    void setMovementBehaviour(IMovementBehaviour movementBehaviour);
    void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour);
}