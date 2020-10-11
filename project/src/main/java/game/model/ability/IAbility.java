package game.model.ability;

import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;

// An ability is something which in some way manipulates the game environment.
// By default, an ability has a user and a target, however, target may be null,
// if an ability does not affect (or target) any one particular entity.
public interface IAbility {
    // When an ability is used, it returns an ability action. This action is handled by the game, and defines
    // how the game/level contents are to be manipulated. If the ability is not ready to be used,
    // the use method will return null. This return value should then be discarded by the game class.
    IAbilityAction use(IEntity<?> user, IEntity<?> target);

    // Each ability has a cooldown, which is the time (in nanoseconds) before the ability can be activated again.
    long getCooldown();

    double getCooldownCountdown();

}