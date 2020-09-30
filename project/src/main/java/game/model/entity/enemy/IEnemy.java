package game.model.entity.enemy;

import game.model.entity.movable.ILiving;
import game.model.entity.movable.IMovable;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.IAbilityBehaviour;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.shape2d.ICircle;

public interface IEnemy extends ILiving, IMovable<ICircle> {
    void setMovementBehaviour(IMovementBehaviour movementBehaviour);
    void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour);
    boolean setTarget(IEntity<?> target);

    IAbilityAction applyAbility();
}
