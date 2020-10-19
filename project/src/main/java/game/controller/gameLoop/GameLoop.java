/*
 * Authors: Anton Hildingsson 
 */

package game.controller.gameLoop;

import game.util.Timer;
import javafx.animation.AnimationTimer;

import java.util.concurrent.TimeUnit;

// A game loop implementation. This code is abstracted away
// to ensure the model code does not have to implement it. Model code could extend this class,
// or (even better) let a controller handle the game loop.
public abstract class GameLoop implements IGameLoop {
    // A second in nanoseconds. This value is used for various calculations, for example when
    // converting between seconds and nanoseconds.
    public final static long SECOND = 1_000_000_000;

    // Fields used for managing the game loop.
    private final long nanosPerFrame;   // The number of nano seconds per frame at desired FPS
    private long lastNanoTime;          // Last sampled nano time. Used to calculate delta.
    private boolean isRunning;          // True if timer is running.
    private final AnimationTimer animationTimer; // Timer used for handling game loop.
    private boolean paused = true;      // True if the loop is paused

    // Fields for calculating FPS
    private long lastSecond;      // Nanos when FPS was calculated last
    private int framesThisSecond; // How many frames that have passed this second
    private int currentFPS;       // The currently calculated FPS

    // Timer
    private final Timer timer;

    public GameLoop(int desiredFPS) {
        timer = new Timer();
        setPaused(true);
        // The number of nanoseconds which will elapse each frame at desired FPS.
        this.nanosPerFrame = SECOND / desiredFPS;
        // The internal timer which will run the loop.
        animationTimer = new AnimationTimer() {
            /* Handled is called every iteration. Now is the current system nano time. */
            public void handle(long now) {
                // The amount of nanoseconds which have passed since the previous frame.
                long elapsedNanos = now - lastNanoTime;

                // Only update if elapsed nanos has exceeded nanos a frame. This ensures the frame rate is limited
                // to desiredFPS. The FPS might be lower, however.
                if (elapsedNanos > nanosPerFrame) {
                    // Calculate delta: change in nanoseconds divided by 10^9 (1 second in nanosecond)
                    // This value should be used by objects implementing the updatable interface.
                    // By knowing the amount of time which has passed since the last frame, an object can
                    // make sure it runs equally fast/slow, no matter the frame rate.
                    double delta = (double) elapsedNanos / SECOND;

                    // If the game is not paused, update
                    if (!paused) {
                        update(delta);
                        timer.setTime(delta);
                    }

                    // Set the last nano time to the current time. Will be used to calculate elapsedNanos next frame
                    lastNanoTime = now;

                    // Update the number of frames which have passed this second
                    framesThisSecond++;
                }

                // Calculate current FPS if a second or more has passed.
                if(now - lastSecond >= SECOND) {
                    // Set the last second value to now
                    lastSecond = now;
                    // Set current FPS to how many frames have passed this second
                    currentFPS = framesThisSecond;
                    // Reset frames this second
                    framesThisSecond = 0;
                }
            }
        };
    }

    // Delta signifies the change in time since last update.
    @Override
    public abstract void update(double delta);

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void setPaused(boolean paused) {
       this.paused = paused;
    }

    // Starts the loop. This method should be called after initializing the object, to start the
    // animation timer.
    @Override
    public void start() {
        isRunning = true;
        animationTimer.start();
    }

    // Stops the loop.
    @Override
    public void stop() {
        isRunning = false;
        animationTimer.stop();
    }

    @Override
    public boolean isPaused() {
        return paused;
    }

    @Override
    public int getCurrentFPS() {
        return currentFPS;
    }

    @Override
    public String getTime() {
        return timer.getTime();
    }

    @Override
    public void resetTimer () {
        timer.resetTimer();
    }
}
