package game.view.pages.score;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HighscorePanel extends VBox {

    public HighscorePanel () {
        this.getStyleClass().add("highscorePanel");
        createScoreEntry(1, 20.221);
        createScoreEntry(2, 2.221);
        createScoreEntry(3, 20.3122);

        this.mouseTransparentProperty().setValue(true);
        this.setVisible(false);
    }

    public void createScoreEntry (int levelNr, double time) {
        HBox entryContainer = new HBox();

        Label level = new Label(String.format("%d: ", levelNr));
        Label score = new Label(String.format("%.1f", time));

        entryContainer.getChildren().addAll(
                level,
                score
        );

        this.getChildren().add(entryContainer);

    }

}
