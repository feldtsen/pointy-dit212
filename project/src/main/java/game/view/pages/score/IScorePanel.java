/*
 * Authors: Mattias Oom, Joachim Ørfeldt Pedersen
 *
 * Panel for displaying the current score of the player.
 */ 

package game.view.pages.score;


public interface IScorePanel {
    // Update the score panel with the current score in seconds.
    void updateScore(double score);
}
