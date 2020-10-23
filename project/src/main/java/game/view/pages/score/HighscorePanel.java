/*
 * Authors: Joachim Pedersen, Mattias Oom
 *
 * Displays the player score for each level. This is visible
 * when viewing scores, but not during gameplay.
 */

package game.view.pages.score;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HighscorePanel extends VBox {

    public HighscorePanel () {
        // Set styling
        this.getStyleClass().add("highscorePanel");

        // Makes sure you can click "through" the panel, i.e interact
        // with nodes below.
        this.mouseTransparentProperty().setValue(true);

    }

    // Set score view
    public void createScoreEntry (String level, double time) {
        // Highscore entry
        Label entry = new Label("Level " + level + " - " + String.format("%.1fs", time));

        this.getChildren().add(
                entry
        );

    }
}
