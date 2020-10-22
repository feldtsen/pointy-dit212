package game.view.pages.level;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LevelPanel extends FlowPane {

    public LevelPanel() {
        this.getStyleClass().add("levelPanel");

        this.setVisible(false);

        this.setMaxHeight(500);

    }

    public void createEntry (Button load) {
        this.getChildren().add(load);
    }

}
