package game.view.pages.level;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LevelPanel extends VBox {

    public LevelPanel() {
        this.getStyleClass().add("levelPanel");
        setAlignment(Pos.CENTER);

    }

    public void createEntry (Button load) {
        this.getChildren().add(load);
    }

}
