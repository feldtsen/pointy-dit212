package game.model.score;

public interface IHighscoreHandler {
    void storedHighscore(String level);
    void writeToFile(String level, double time);
    void readFromFile();
    void createFile();
}
