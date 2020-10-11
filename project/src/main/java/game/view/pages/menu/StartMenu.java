package game.view.pages.menu;

import game.controller.GameWindowController;
import game.view.pages.menu.buttons.ExitButton;
import game.view.pages.menu.buttons.LevelButton;
import game.view.pages.menu.buttons.ScoreButton;
import game.view.pages.menu.buttons.StartButton;
import javafx.animation.FadeTransition;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class StartMenu extends HBox {
    private final static String MENU_BUTTON_CLASS = "menuButton";

    public StartMenu(GameWindowController gameWindowController) {
        StartButton startButton = new StartButton("START");
        LevelButton levelButton = new LevelButton("LEVEL");
        ScoreButton scoreButton = new ScoreButton("SCORE");
        ExitButton exitButton   = new ExitButton("EXIT");

        // Connect button to an event
        startButton.setOnMouseClicked(e -> gameWindowController.handleMenuStartButton());
        levelButton.setOnMouseClicked(e -> gameWindowController.handleMenuLevelButton());
        scoreButton.setOnMouseClicked(e -> gameWindowController.handleMenuScoreButton());
        exitButton.setOnMouseClicked( e -> gameWindowController.handleMenuExitButton());




        // Set css classes for styling
        this.getStyleClass().add("startMenu");
        startButton.getStyleClass().add(MENU_BUTTON_CLASS);
        exitButton.getStyleClass().add(MENU_BUTTON_CLASS);
        levelButton.getStyleClass().add(MENU_BUTTON_CLASS);
        scoreButton.getStyleClass().add(MENU_BUTTON_CLASS);

        // Add menu buttons to the menu container
        this.getChildren().addAll(
                startButton,
                levelButton,
                scoreButton,
                exitButton
        );

    }

}
