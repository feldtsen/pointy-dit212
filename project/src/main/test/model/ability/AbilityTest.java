package model.ability;

import game.model.ability.Ability;
import game.model.ability.IAbility;
import game.model.ability.ShootBullet;
import game.model.ability.action.AbilityAction;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.Player;
import game.model.level.ILevel;
import javafx.geometry.Point2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbilityTest {
    private IEntity<?> dummy1 = new Player(new Point2D(0, 0), 1, 1, 1);
    private IEntity<?> dummy2 = new Player(new Point2D(0, 0), 1, 1, 1);

    private IAbilityAction getAction() {
        return new IAbilityAction() {
            @Override
            public double getDuration() {
                return 0;
            }

            @Override
            public void apply(ILevel level, double timePassed) {
            }
        };
    }

    private IAbility getAbility(long cooldown) {
        Ability ability = new Ability(cooldown) {
            @Override
            public IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
                IAbilityAction action = new AbilityAction(user, target, 0) {
                    @Override
                    public void apply(ILevel level, double timePassed) { }
                };
                return action;
            }
        };
        return ability;
    }


    @Test
    public void testUseOffCooldown(){
        long cooldown = 4000;

        IAbility a1 = getAbility(cooldown);

        assertNotNull(a1.use(dummy1, dummy2));

    }



    @Test
    public void testUseOnCooldown(){
        long cooldown = 10000000;

        IAbility a1 = getAbility(cooldown);
        a1.use(dummy1, dummy2);
        assertNull(a1.use(dummy1, dummy2));
    }

    @Test
    public void testUseAfterCooldown() throws InterruptedException {

        long cooldown = 100;
        IAbility a1 = getAbility(cooldown);

        a1.use(dummy1, dummy2);
        Thread.sleep(cooldown +1);
        assertNotNull(a1.use(dummy1, dummy2));

    }

}
