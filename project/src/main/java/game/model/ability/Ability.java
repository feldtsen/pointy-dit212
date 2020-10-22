/*
 * Authors: Anton Hildingsson, Joachim Pedersen, Mattias Oom, Simon Genne
 *
 * Abstract helper implementation of IAbility, which simplifies creating new abilities.
 * This is possible since all abilities will share the same cool down functionality.
 */

package game.model.ability;

import game.controller.gameLoop.GameLoop;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;

public abstract class Ability implements IAbility{
    // The time it takes (in nanoseconds) before the ability can be activated again
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
    public long getCooldown(){
        return cooldown;
    }

    // Returns the time until the ability can be used again in percentage until ready
    public double getCooldownCountdownPercentage() {
        return 100 - ((getCooldownCountdown() / ((double)cooldown/ GameLoop.SECOND)) * 100);
    }

    // Returns the time until the ability can be used again in seconds
    @Override
    public double getCooldownCountdown() {
        // Since System.nanoTime() - lastUsed can surpass cooldown, we limit the retrieval to be min 0
        return Math.max((double)(cooldown - (System.nanoTime() - lastUsed))/GameLoop.SECOND, 0);
    }

    // Applies the ability by returning an ability action, or null if the ability cannot be applied yet (due to cooldown).
    // The returned ability action needs to be applied by the class handling the ability, and handled for the amount of
    // time specified in the duration of the ability aciton.
    @Override
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
    // The returned ability actions will specify all actions and effects the ability will
    // affect its surroundings with.
    protected abstract IAbilityAction createAction(IEntity<?> user, IEntity<?> target);
}



