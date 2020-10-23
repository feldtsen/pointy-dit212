/*
 * Authors: Joachim Pedersen, Mattias Oom
 *
 * Displays the current score (time) of the player. This value
 * is updated continuously during gameplay.
 */

package game.view.pages.score;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// Used for displaying the players current score.
public class ScorePanel extends VBox implements IScorePanel {
    private final Label score = new Label();

    public ScorePanel() {
        // Set id for css.
        setId("scorePanel");

        // Set css classes
        this.getStyleClass().add("scorePanel");
        Label scoreLabel = new Label(" T I M E ");
        scoreLabel.getStyleClass().add("scoreLabel");
        score.getStyleClass().add("score");

        // Sets time display to top center of screen.
        this.setAlignment(Pos.TOP_CENTER);
        this.setVisible(false);
        this.getChildren().addAll(
                scoreLabel,
                score
        );

    }

    @Override
    public void updateScore(double score) {
        this.score.setText(String.format("%.1f", score));
    }
}
