/*
 * Authors: Joachim Pedersen
 *
 * Panel for displaying acquired levels
 */
package game.view.pages.level;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LevelPanel extends VBox {

    public LevelPanel() {

        // Sets css classes
        this.getStyleClass().add("levelPanel");
        setAlignment(Pos.CENTER);

    }
    // Adds reached level button with a corresponding action to level panel
    public void createEntry (Button load) {
        this.getChildren().add(load);
    }

}
