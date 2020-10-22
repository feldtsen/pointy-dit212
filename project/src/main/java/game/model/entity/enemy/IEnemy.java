/*
 * Authors: Mattias Oom, Anton Hildingsson, Simon Genne
 *
 * Enemies represent hostile elements in the game. They have a target
 * which they typically move towards and aim at when using their abilities.
 */

package game.model.entity.enemy;

import game.model.entity.movable.ILiving;
import game.model.entity.movable.IMovable;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.IAbilityBehaviour;
import game.model.behavior.movement.IMovementBehaviour;
import game.model.entity.IEntity;
import game.model.shape2d.ICircle;


public interface IEnemy extends ILiving, IMovable<ICircle> {
    // The movement behavior of an enemy defines how it moves across the map
    void setMovementBehaviour(IMovementBehaviour movementBehaviour);

    // The ability behavior defines the set of abilities of the enemy, and how it uses them
    void setAbilityBehaviour(IAbilityBehaviour abilityBehaviour);

    // Target may be null, if the enemy has no particular target.
    boolean setTarget(IEntity<?> target);

    // Applies an ability. Is usually called every frame, however, since abilities
    // have cooldown and should return null if the cooldown is active, this method will often
    // return null
    IAbilityAction applyAbility();
}
