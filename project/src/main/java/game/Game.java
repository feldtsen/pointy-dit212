package game;

import javafx.animation.AnimationTimer;

public class Game implements IGame {
    private final static long SECOND = 1_000_000_000;

    /* Fields used for managing the game loop. */
    private final long nanosPerFrame;   /* The number of nano seconds per frame at desired FPS */
    private long lastNanoTime;          /* Last sampled nano time. Used to calculate delta. */
    private final AnimationTimer timer; /* Timer used for handling game loop. */
    private boolean isRunning;          /* True if timer is running. */

    /* Fields for calculating FPS */
    private long lastSecond;
    private int framesThisSecond;
    private int currentFPS;

    /* Player/level specific fields. */
    private final int score;            /* Player score. */

    public Game(int desiredFPS) {
        this.score = 0;

        /* The number of nano seconds which will elapse each frame at desired FPS. */
        this.nanosPerFrame = SECOND / desiredFPS;

        timer = new AnimationTimer() {
            /* Handled is called every iteration. Now is the current system nano time. */
            public void handle(long now) {
                long elapsedNanos = now - lastNanoTime;
                /* Only update if elapsed nanos has exceeded nanos a frame. This ensures the frame rate is limited
                 * to desiredFPS. The FPS might be lower, however.
                 */
                if (elapsedNanos > nanosPerFrame) {
                    /* Calculate delta: change in nanoseconds divided by 10^9 (1 second in nanosecond) */
                    double delta = (double)elapsedNanos / SECOND;

                    update(delta);

                    lastNanoTime = now;
                    framesThisSecond++;
                }

                if(now - lastSecond >= SECOND) {
                    lastSecond = now;
                    currentFPS = framesThisSecond;
                    framesThisSecond = 0;
                    System.out.println(currentFPS);
                }
            }
        };
    }

    /* Delta signifies the change in time since last update. */
    private void update(double delta) {
        /* TODO: Game code goes here... */


    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void start() {
        isRunning = true;
        timer.start();
    }

    @Override
    public void stop() {
        isRunning = false;
        timer.stop();
    }

    @Override
    public int getCurrentFPS() {
        return currentFPS
    }
}
