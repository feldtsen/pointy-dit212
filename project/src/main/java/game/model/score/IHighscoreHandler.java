/*
 * Authors: Joachim Ørfeldt Pedersen, Mattias Oom
 */

package game.model.score;

public interface IHighscoreHandler {
    // Given a level, returns the highscore for that level
    double getHighscore(String level);

    // Set a given level, a given score
    void setHighscore(String level, double score);

    // Writes the stored highscore datastructure to a text file
    void writeToFile();

    // Loads stored text file of highscores into a datastructure referenced while game is running
    void readFromFile();

    // Creates a highscore.txt file if the file does not exist
    void createFile();
}
