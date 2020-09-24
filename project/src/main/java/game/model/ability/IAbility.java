package game.model.ability;


import game.model.ability.action.IAbilityAction;

public interface IAbility {
    IAbilityAction use();
    long getCooldown();
}