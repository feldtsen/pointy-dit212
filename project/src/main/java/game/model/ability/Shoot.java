package game.model.ability;

import game.model.ability.action.AbilityAction;
import game.model.ability.action.IAbilityAction;
import game.model.entity.Entity;
import game.model.entity.IEntity;
import game.model.entity.movable.LivingEntity;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Projectile;
import game.model.level.ILevel;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.List;

public abstract class Shoot extends Ability {

    public Shoot(long cooldown) {
        super(cooldown);
    }

    @Override
    // The returned AbilityAction creates and adds a bullet to the levels list of projectiles.
    public IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
        return new AbilityAction(0) {

            @Override
            public void apply(ILevel level, double timePassed) {
                // Create new projectile with method declared in subclasses.
                IProjectile<?> projectile = createProjectile(user, target);

                // Get levels list of projectiles and add projectile.
                List<IProjectile<?>> projectiles = level.getProjectiles();
                projectiles.add(projectile);
            }
        };
    }

    // Concrete subclasses will implement createProjectile to determine what kind of projectiles the Ability should add.
    abstract IProjectile<?> createProjectile(IEntity<?> user, IEntity<?> target);
}
