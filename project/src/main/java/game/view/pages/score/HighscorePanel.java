/*
 * Authors: Joachim Pedersen, Mattias Oom
 *
 * Displays the player score for each level. This is visible
 * when viewing scores, but not during gameplay.
 */

package game.view.pages.score;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HighscorePanel extends VBox {

    public HighscorePanel () {
        // Set styling
        this.getStyleClass().add("highscorePanel");

        // Makes sure you can click "through" the panel, i.e interact
        // with nodes below.
        this.mouseTransparentProperty().setValue(true);

        // Start of as invisible
        this.setVisible(false);
    }

    // Set score view
    public void createScoreEntry (String level, double time) {
        HBox entryContainer = new HBox();

        // Level ID
        Label lvl = new Label(level + ": ");

        // Score value
        Label score = new Label(String.format("%.1f", time));

        entryContainer.getChildren().addAll(
                lvl,
                score
        );

        this.getChildren().add(entryContainer);
    }
}
