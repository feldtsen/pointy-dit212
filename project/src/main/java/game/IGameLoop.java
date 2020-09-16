package game;

public interface IGameLoop {
    boolean isRunning();
    void start();
    void stop();
    int getCurrentFPS();
    void update(double delta);
}
