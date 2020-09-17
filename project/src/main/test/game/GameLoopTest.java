package game;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameLoopTest {

    @Test
    public void TestFPS60() {
        assertTrue(true); //TODO: dummy test! how to test gameloop?
        /*int desiredFPS = 60;
        IGameLoop gameLoop = new GameLoop(desiredFPS) {
            @Override
            public void update(double delta) {
            }
        };
        gameLoop.start();

        int iterations = 5;
        double averageFPS = 0;

        for(int i = 0; i < iterations; i++) {
            try {
                Thread.sleep(1_000_000_000);
            } catch (InterruptedException e) {
                fail();
            }
            averageFPS += (double)gameLoop.getCurrentFPS() / iterations;
        }

        assert(averageFPS <= desiredFPS);*/
    }

}
