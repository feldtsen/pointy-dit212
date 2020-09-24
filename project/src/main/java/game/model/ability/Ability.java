package game.model.ability;

import game.model.ability.action.IAbilityAction;
import game.model.level.ILevel;

public abstract class Ability implements IAbility{
    private final IAbilityAction action;
    private final long cooldown;
    private long lastUsed;

    public Ability(long cooldown, IAbilityAction action) {
        this.action = action;
        this.cooldown = cooldown;
        this.lastUsed = 0;
    }

    @Override
    public long getCooldown(){return cooldown;}

    public IAbilityAction use(){
        long currentTime = System.nanoTime();
        if (currentTime - lastUsed >= cooldown){
            lastUsed = currentTime;
            return action;
        }
        return null;
    }
}



