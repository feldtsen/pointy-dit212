package game.view.pages.gameOver;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// Panel shown after the player has lost.
public class GameOverPanel extends VBox {
    private static final  String GAME_OVER_LABEL_CSS = "gameOverLabel";
    private static final String GAME_OVER_INSTRUCTIONS_CSS = "gameOverInstructions";

    Label gameOverLabel;
    Label gameOverInstructions;

    public GameOverPanel() {
        this.gameOverLabel = new Label("GAME OVER");
        this.gameOverInstructions = new Label("Press P to play again, or ESC to go the menu.");

        gameOverLabel.getStyleClass().add(GAME_OVER_LABEL_CSS);
        gameOverInstructions.getStyleClass().add(GAME_OVER_INSTRUCTIONS_CSS);

        this.getChildren().setAll(
                gameOverLabel,
                gameOverInstructions
        );

        // Panel should not be clickable and should be hidden by default.
        this.setMouseTransparent(true);
        this.setVisible(false);
    }
}
