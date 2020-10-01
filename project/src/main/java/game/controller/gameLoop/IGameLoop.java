package game.controller.gameLoop;

public interface IGameLoop {
    // True if the game loop is currently running
    boolean isRunning();

    int getCurrentFPS();

    // Pausing stops the game loop from calling the update method, but doesn't stop the internal timer.
    boolean isPaused();
    void setPaused(boolean paused);

    // Start and stop starts and stops the internal timer
    void start();
    void stop();

    void update(double delta);
}
