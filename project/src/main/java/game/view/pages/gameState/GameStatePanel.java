/*
 * Authors: Simon Genne, Joachim Pedersen, Erik Magnusson
 *
 * Panel shown at different states of the game, e.g. winning, game over, level transition.
 */

package game.view.pages.gameState;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameStatePanel extends VBox {
    // Names of CSS styles
    private final static String GAME_STATE_LABEL_CSS = "gameStateLabel";
    private final static String GAME_STATE_INSTRUCTIONS_CSS = "gameStateInstructions";

    public GameStatePanel(String message, String instructions) {
        // Message
        Label gameStateLabel = new Label(message);
        // Instructions
        Label gameStateInstructions = new Label(instructions);

        // set styles
        gameStateLabel.getStyleClass().add(GAME_STATE_LABEL_CSS);
        gameStateInstructions.getStyleClass().add(GAME_STATE_INSTRUCTIONS_CSS);
        this.getStyleClass().add("gameStatePanel");

        this.getChildren().setAll(
                gameStateLabel,
                gameStateInstructions
        );

        // Panel should not be clickable and should be hidden by default.
        this.setMouseTransparent(true);
        this.setVisible(false);
    }

    // Initiates empty panel
    public GameStatePanel() {
        this("","");
    }
}


