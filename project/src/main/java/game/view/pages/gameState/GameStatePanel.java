/*
 * Authors: Simon Genne, Joachim Pedersen, Erik Magnusson
 */

package game.view.pages.gameState;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// Panel shown at different states of the game like, winning, game over, level transition.
public class GameStatePanel extends VBox {

    public GameStatePanel(String gameStateMessage, String gameStateInstructions) {
        String GAME_STATE_LABEL_CSS = "gameStateLabel";
        String GAME_STATE_INSTRUCTIONS_CSS = "gameStateInstructions";
        Label gameStateLabel = new Label(gameStateMessage);
        Label gameStateInstructions1 = new Label(gameStateInstructions);

        gameStateLabel.getStyleClass().add(GAME_STATE_LABEL_CSS);
        gameStateInstructions1.getStyleClass().add(GAME_STATE_INSTRUCTIONS_CSS);

        this.getChildren().setAll(
                gameStateLabel,
                gameStateInstructions1
        );

        // Panel should not be clickable and should be hidden by default.
        this.setMouseTransparent(true);
        this.setVisible(false);
    }
}


