package model.ability;

import game.model.ability.Ability;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbilityTest {

    @Test
    public void testUseOffCooldown(){
        long cooldown = 4000;

        Ability a1 = new Ability(cooldown) {@Override public void activate(){}};

        assertTrue(a1.use());

    }

    @Test
    public void testUseOnCooldown(){
        long cooldown = 5550;

        Ability a1 = new Ability(cooldown) {@Override public void activate(){}};

        a1.use();
        assertFalse(a1.use());


    }

    @Test
    public void testUseAfterCooldown() throws InterruptedException {

        long cooldown = 100;
        Ability a1 = new Ability(cooldown) {@Override public void activate(){}};

        a1.use();
        Thread.sleep(cooldown +1);
        assertTrue(a1.use());


    }

}
