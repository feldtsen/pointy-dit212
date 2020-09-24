package game.model.gameLoop;

import javafx.animation.AnimationTimer;

public abstract class GameLoop implements IGameLoop {
    public final static long SECOND = 1_000_000_000;

    /* Fields used for managing the game loop. */
    private final long nanosPerFrame;   /* The number of nano seconds per frame at desired FPS */
    private long lastNanoTime;          /* Last sampled nano time. Used to calculate delta. */
    private boolean isRunning;          /* True if timer is running. */
    private final AnimationTimer timer; /* Timer used for handling game loop. */
    private boolean paused = true;

    /* Fields for calculating FPS */
    private long lastSecond;
    private int framesThisSecond;
    private int currentFPS;

    public GameLoop() {
        /* Improbably high FPS, ensures animation timer can set FPS itself */
        this(1000);
    }

    public GameLoop(int desiredFPS) {
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

                    if (!paused) update(delta);

                    lastNanoTime = now;
                    framesThisSecond++;
                }

                if(now - lastSecond >= SECOND) {
                    lastSecond = now;
                    currentFPS = framesThisSecond;
                    framesThisSecond = 0;
                    //System.out.println(currentFPS);
                }
            }
        };
    }

    /* Delta signifies the change in time since last update. */
    public abstract void update(double delta);

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void setPaused(boolean paused) {
       this.paused = paused;
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
    public boolean isPaused() {
        return paused;
    }

    @Override
    public int getCurrentFPS() {
        return currentFPS;
    }
}
