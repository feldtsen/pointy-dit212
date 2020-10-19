package game.view.pages.score;

import game.model.entity.player.IPlayer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

// Used for displaying the players current score.
public class ScorePanel extends VBox implements IScorePanel {
    private Label scoreLabel = new Label();
    private Label score = new Label();

    public ScorePanel() {
        // Set id for css.
        setId("scorePanel");

        this.getStyleClass().add("scorePanel");
        scoreLabel.getStyleClass().add("scoreLabel");
        score.getStyleClass().add("score");

        this.setAlignment(Pos.TOP_CENTER);

        this.getChildren().addAll(
                scoreLabel,
                score
        );

    }

    @Override
    // Update the score panel with the current score in seconds.
    public void updateScore(String score) {
        if(scoreLabel.getText().equals("")) scoreLabel.setText(" T I M E ");
        this.score.setText(score);
    }
}
