package game.model.entity.enemy;

import game.model.ILiving;
import game.model.IMovable;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.IAbilityBehaviour;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.shape2d.ICircle;


// Enemies represent hostile elements in the game
public interface IEnemy extends ILiving, IMovable<ICircle> {
    // The movement behavior of an enemy defines how it moves across the map
    void setMovementBehaviour(IMovementBehaviour movementBehaviour);

    // The ability behavior defines the set of abilities of the enemy, and how it uses them
    void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour);

    // Target may be null, if the enemy has no particular target.
    boolean setTarget(IEntity<?> target);

    IAbilityAction applyAbility();
}
