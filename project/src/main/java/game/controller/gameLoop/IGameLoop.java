/*
 * Authors: Anton Hildingsson, Mattias Oom, Joachim Pedersen
 */

package game.controller.gameLoop;

// A simple game loop implementation. The loop will call update
// after it has been started, and if it is not paused.
public interface IGameLoop {
    // True if the game loop is currently running
    boolean isRunning();

    int getCurrentFPS();

    // Pausing stops the game loop from calling the update method, but doesn't stop the internal timer.
    boolean isPaused();
    void setPaused(boolean paused);

    // Start and stop starts and stops the internal timer.
    void start();
    void stop();

    // Update is called once each loop iteration.
    // delta is a value representing the number of seconds which have passed since the previous iteration.
    // This value should be used by all classes implementing the updatable interface, to ensure they run
    // equally fast/slow independent of loop frame rate.
    void update(double delta);

}
