/*
 * Authors: Anton Hildingsson, Mattias Oom, Simon Genne
 *
 * Ability for shooting projectiles. This class is abstract 
 * since it does not define which projectile to shoot. This will 
 * be done by implementing classes.
 */

package game.model.ability;

import game.model.ability.action.AbilityAction;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;

import java.util.List;

public abstract class Shoot extends Ability {

    public Shoot(long cooldown) {
        super(cooldown);
    }

    @Override
    // The returned AbilityAction creates and adds a bullet to the levels list of projectiles.
    public IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
        return new AbilityAction(user, 0) {

            @Override
            public void apply(ILevel level, double timePassed) {
                // Create new projectile with method declared in subclasses.
                IProjectile<?> projectile = createProjectile(user, target);

                // Get levels list of projectiles and add projectile.
                List<IProjectile<?>> projectiles = level.getProjectiles();
                projectiles.add(projectile);
            }

            @Override
            public void onFinished(ILevel level) {
            }
        };
    }

    // Concrete subclasses will implement createProjectile to determine what kind of projectiles the Ability should add.
    protected abstract IProjectile<?> createProjectile(IEntity<?> user, IEntity<?> target);
}
