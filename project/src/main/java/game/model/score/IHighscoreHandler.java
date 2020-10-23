/*
 * Authors: Joachim Ørfeldt Pedersen, Mattias Oom
 *
 * Abstraction for methods used to write and read highscores to/from disk.
 * This is used to save the player results.
 */

package game.model.score;

import java.util.HashMap;

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

    // Gets all the current stored highscores
    HashMap<String, Double> getStoredHighscores();
}
