package game;

public interface IGame {
    // TODO: add when level interface is done!
    //ILevel getCurrentLevel();
    //List<ILevel> getLevels();
    int getScore();
    boolean isRunning();
    void start();
    void stop();

    int getCurrentFPS();
}
