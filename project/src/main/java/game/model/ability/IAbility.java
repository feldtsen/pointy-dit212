package game.model.ability;


import game.model.ability.action.IAbilityAction;
import game.model.entity.Entity;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;

public interface IAbility {
    IAbilityAction use(IEntity<?> user, IEntity<?> target);
    long getCooldown();
}