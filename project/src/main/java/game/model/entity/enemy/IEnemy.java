package game.model.entity.enemy;

import game.model.ILiving;
import game.model.IMovable;
import game.model.behavior.ability.IAbilityBehaviour;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.shape2d.IShape2D;

public interface IEnemy<T extends IShape2D> extends ILiving, IMovable {
    void setMovementBehaviour(IMovementBehaviour movementBehaviour);
    void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour);
    boolean setTarget(IEntity<?> target);
}
