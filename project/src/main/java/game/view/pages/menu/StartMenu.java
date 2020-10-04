package game.view.pages.menu;

import game.view.pages.menu.buttons.StartButton;
import javafx.scene.layout.HBox;

public class StartMenu extends HBox {
    public StartMenu() {
        StartButton startButton = new StartButton("Start");


        // Add menu buttons
        this.getChildren().addAll(
                startButton
        );

    }
}
