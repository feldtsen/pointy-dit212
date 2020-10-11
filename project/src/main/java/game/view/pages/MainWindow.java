package game.view.pages;

import game.controller.GameWindowController;
import game.view.ViewResourceLoader;
import game.view.pages.abilityBar.AbilityBar;
import game.view.pages.abilityBar.AbilityHolder;
import game.view.pages.canvas.GameCanvas;
import game.view.pages.menu.StartMenu;
import game.view.pages.score.ScorePanel;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MainWindow extends StackPane {
    private static final String MAIN_WINDOW_CSS = "mainWindow";

    GameCanvas gameCanvas;
    StartMenu startMenu;
    ScorePanel scorePanel;
    AbilityBar abilityBar;

    public MainWindow(GameWindowController gameWindowController) {
        gameCanvas = new GameCanvas();
        startMenu  = new StartMenu(gameWindowController);
        scorePanel = new ScorePanel();
        abilityBar = new AbilityBar();


        // Add a stylesheet
        this.getStylesheets().add(ViewResourceLoader.stylesheet);

        // Add class for styling
        this.getStyleClass().add(MAIN_WINDOW_CSS);

        // Animations
        ViewResourceLoader.fadeIn().setNode(startMenu);

        // Add what you want to display
        this.getChildren().setAll(
                gameCanvas,
                abilityBar,
                scorePanel,
                startMenu

        );

        // Align based on window container
        setAlignment(scorePanel, Pos.TOP_RIGHT);
        setAlignment(abilityBar, Pos.BOTTOM_CENTER);

        // Align inside their respective container
        startMenu.setAlignment(Pos.BOTTOM_CENTER);
        abilityBar.setAlignment(Pos.CENTER);


        // Bind the size of different components to the window size, making the components responsive
        // (relative to its parent)
        gameCanvas.widthProperty().bind(this.widthProperty());
        gameCanvas.heightProperty().bind(this.heightProperty());


    }

    public GameCanvas getGameCanvas () {
        return gameCanvas;
    }

    public ScorePanel getScorePanel () {return scorePanel;}

    public AbilityBar getAbilityBar () {
        return abilityBar;
    }

    public void menuFadeIn() {
        ViewResourceLoader.fadeIn().setNode(startMenu);
        // Making all children of start menu transparent to mouse events
        startMenu.setMouseTransparent(true);
    }

    public void menuFadeOut() {
        ViewResourceLoader.fadeOut().setNode(startMenu);
        // Making all children of start menu visible to mouse events
        startMenu.setMouseTransparent(false);
    }



}
