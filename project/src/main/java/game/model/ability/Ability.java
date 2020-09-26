package game.model.ability;

import game.model.ability.action.IAbilityAction;
import game.model.entity.Entity;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import game.model.level.ILevel;

public abstract class Ability implements IAbility{
    private final long cooldown;
    private long lastUsed;

    public Ability(long cooldown) {
        this.cooldown = cooldown;
        this.lastUsed = 0;
    }

    @Override
    public long getCooldown(){return cooldown;}

    public IAbilityAction use(IEntity<?> user, IEntity<?> target){
        long currentTime = System.nanoTime();
        if (currentTime - lastUsed >= cooldown){
            lastUsed = currentTime;
            return createAction(user, target);
        }
        return null;
    }

    public abstract IAbilityAction createAction(IEntity<?> user, IEntity<?> target);
}



