package game.model.ability;

import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;

// Abstract helper implementation of IAbility, which simplifies creating new abilities.
// This is possible since all abilities will share the same cool down functionality.
public abstract class Ability implements IAbility{
    private final long cooldown;

    // Stores the time when the ability was last used. This value is used to check if the ability is ready to
    // be used again.
    private long lastUsed;

    public Ability(long cooldown) {
        this.cooldown = cooldown;

        // Set to 0, an improbably low value, to ensure that ability is ready to be used on initialization.
        // 0 is a low value, since lastUsed will be set to the value of System.nanoTime(), which is a incredibly large
        // long value.
        this.lastUsed = 0;
    }

    @Override
    public long getCooldown(){return cooldown;}

    public IAbilityAction use(IEntity<?> user, IEntity<?> target){
        // Store the current nano time
        long currentTime = System.nanoTime();
        // Check if the time passed exceeds that of cool down
        if (currentTime - lastUsed >= cooldown){
            lastUsed = currentTime;
            // If true, create an ability action and return
            return createAction(user, target);
        }
        // Return null if cool down is still active.
        return null;
    }

    // This method will be implemented by all ability implementations.
    public abstract IAbilityAction createAction(IEntity<?> user, IEntity<?> target);
}



