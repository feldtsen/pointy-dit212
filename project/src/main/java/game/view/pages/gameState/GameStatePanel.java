package game.view.pages.gameState;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class GameStatePanel {

    // Panel shown at different states of the game like, winning, game over, level transition.
    public class GameOverPanel extends VBox {

        private final String GAME_STATE_LABEL_CSS;
        private final String GAME_STATE_INSTRUCTIONS_CSS;

        private Label gameStateLabel;
        private Label gameStateInstructions;

        public GameOverPanel(String gameStateName, String gameStateInstructions) {
            this.GAME_STATE_LABEL_CSS = gameStateName;
            this.GAME_STATE_INSTRUCTIONS_CSS = gameStateName + "Instructions";
            this.gameStateLabel = new Label(gameStateName);
            this.gameStateInstructions = new Label(gameStateInstructions);

            this.gameStateLabel.getStyleClass().add(GAME_STATE_LABEL_CSS);
            this.gameStateInstructions.getStyleClass().add(GAME_STATE_INSTRUCTIONS_CSS);

            this.getChildren().setAll(
                    this.gameStateLabel,
                    this.gameStateInstructions
            );

            // Panel should not be clickable and should be hidden by default.
            this.setMouseTransparent(true);
            this.setVisible(false);
        }
    }

}
