package game.model.score;

public interface IHighscoreHandler {
    double getHighscore(String level);
    void setHighscore(String level, double score);
    void writeToFile();
    void readFromFile();
    void createFile();
}
