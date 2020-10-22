package game.view.pages.level;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LevelPanel extends VBox {

    public LevelPanel() {
        this.getStyleClass().add("levelPanel");

        this.mouseTransparentProperty().setValue(true);
        this.setVisible(false);
    }

    public void createEntry (String level) {
        Button load = new Button("Load level " + level);

        this.getChildren().add(load);

    }

}
