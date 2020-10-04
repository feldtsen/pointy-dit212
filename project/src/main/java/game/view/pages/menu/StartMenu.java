package game.view.pages.menu;

import game.view.pages.menu.buttons.ExitButton;
import game.view.pages.menu.buttons.StartButton;
import javafx.scene.layout.HBox;

public class StartMenu extends HBox {
    private final static String MENU_BUTTON_CLASS = "menuButton";

    public StartMenu() {
        StartButton startButton = new StartButton("Start");
        ExitButton exitButton  = new ExitButton("Exit");

        startButton.getStyleClass().add(MENU_BUTTON_CLASS);
        exitButton.getStyleClass().add(MENU_BUTTON_CLASS);

        startButton.setId("startButton");
        exitButton.setId("exitButton");

        // Add menu buttons
        this.getChildren().addAll(
                startButton,
                exitButton
        );

    }

}
