package model.ability;

import game.model.ability.Ability;
import game.model.ability.action.IAbilityAction;
import game.model.level.ILevel;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbilityTest {
    private IAbilityAction getAction() {
        return new IAbilityAction() {
           // @Override
           // public double getRadius() {
           //     return 0;
           // }

            @Override
            public double getDuration() {
                return 0;
            }

            @Override
            public void apply(ILevel level, double timePassed) {
            }
        };
    }

    @Test
    public void testUseOffCooldown(){
        long cooldown = 4000;

        Ability a1 = new Ability(cooldown, getAction()) {};

        assertNotNull(a1.use());

    }

    @Test
    public void testUseOnCooldown(){
        long cooldown = 5550;

        Ability a1 = new Ability(cooldown, getAction()) {};
        a1.use();
        assertNull(a1.use());
    }

    @Test
    public void testUseAfterCooldown() throws InterruptedException {

        long cooldown = 100;
        Ability a1 = new Ability(cooldown, getAction()) {};

        a1.use();
        Thread.sleep(cooldown +1);
        assertNotNull(a1.use());

    }

}
