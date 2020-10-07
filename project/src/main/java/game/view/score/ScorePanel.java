package game.view.score;

import game.model.entity.player.IPlayer;
import javafx.scene.text.Text;

// Used for displaying the players current score.
public class ScorePanel extends Text implements IScorePanel {
    private double windowWidth;
    private double windowHeight;

    public ScorePanel() {
        super();

        // Set id for css.
        setId("scorePanel");

    }

    @Override
    public void updateScore(IPlayer player, int score) {
        setText("Score: " + score);
        // TODO: Check if player is underneath text. If true, change opacity.
    }
}
