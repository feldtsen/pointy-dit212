package game.controller.gameLoop;

public interface IGameLoop {
    // True if the gameloop is currently running
    boolean isRunning();
    int getCurrentFPS();

    boolean isPaused();
    void setPaused(boolean paused);

    void start();
    void stop();

    void update(double delta);
}
