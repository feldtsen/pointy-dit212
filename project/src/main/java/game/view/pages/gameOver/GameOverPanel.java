package game.view.pages.gameOver;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameOverPanel extends VBox {
    Label gameOverLabel;
    Label instructionsLabel;

    public GameOverPanel() {
        this.gameOverLabel = new Label("GAME OVER");
        this.instructionsLabel = new Label("Press ENTER to play again, or ESC to go the menu.");

        gameOverLabel.setAlignment(Pos.CENTER);
        instructionsLabel.setAlignment(Pos.CENTER);

        this.getChildren().setAll(
                gameOverLabel,
                instructionsLabel
        );

        this.setMouseTransparent(true);
        this.setVisible(false);
    }
}
