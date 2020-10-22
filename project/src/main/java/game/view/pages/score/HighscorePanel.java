package game.view.pages.score;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HighscorePanel extends VBox {

    public HighscorePanel () {
        this.getStyleClass().add("highscorePanel");

        this.mouseTransparentProperty().setValue(true);
        this.setVisible(false);
    }

    public void createScoreEntry (String level, double time) {
        HBox entryContainer = new HBox();

        Label lvl = new Label(level + ": ");
        Label score = new Label(String.format("%.1f", time));

        entryContainer.getChildren().addAll(
                lvl,
                score
        );

        this.getChildren().add(entryContainer);

    }

}
