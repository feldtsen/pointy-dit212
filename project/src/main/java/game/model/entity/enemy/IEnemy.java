package game.model.entity.enemy;

import game.model.ILiving;
import game.model.IMovable;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.IAbilityBehaviour;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.level.ILevel;
import game.model.shape2d.ICircle;
import game.model.shape2d.IShape2D;

public interface IEnemy extends IEntity<ICircle>, ILiving, IMovable {
    void setMovementBehaviour(IMovementBehaviour movementBehaviour);
    void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour);
    boolean setTarget(IEntity<?> target);

    IAbilityAction applyAbility(ILevel level, double time);
}
