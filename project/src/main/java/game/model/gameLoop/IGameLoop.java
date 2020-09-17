package game.model.gameLoop;

public interface IGameLoop {
    boolean isRunning();
    void start();
    void stop();
    int getCurrentFPS();
    void setPaused(boolean paused);
    void update(double delta);
}
