package game.view.pages;

import game.controller.GameWindowController;
import game.view.ViewResourceLoader;
import game.view.pages.abilityBar.AbilityBar;
import game.view.pages.abilityBar.AbilityHolder;
import game.view.pages.canvas.GameCanvas;
import game.view.pages.gameOver.GameOverPanel;
import game.view.pages.menu.StartMenu;
import game.view.pages.score.ScorePanel;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MainWindow extends StackPane {
    private static final String MAIN_WINDOW_CSS = "mainWindow";
    private static final String GAME_TITLE = "  J E R K   E V E R T  ";

    public final static FadeTransition fadeTransitionGameMenu = new FadeTransition(new Duration(200));
    public final static FadeTransition fadeTransitionGameTitle = new FadeTransition(new Duration(200));

    GameCanvas gameCanvas;
    StartMenu startMenu;
    ScorePanel scorePanel;
    AbilityBar abilityBar;
    Label gameTitle;
    GameOverPanel gameOverPanel;

    public MainWindow(GameWindowController gameWindowController) {
        gameCanvas = new GameCanvas();
        startMenu  = new StartMenu(gameWindowController);
        scorePanel = new ScorePanel();
        abilityBar = new AbilityBar();
        gameOverPanel = new GameOverPanel();

        gameTitle = new Label(GAME_TITLE);

        // Add a stylesheet
        this.getStylesheets().add(ViewResourceLoader.stylesheet);

        // Add class for styling
        this.getStyleClass().add(MAIN_WINDOW_CSS);
        gameTitle.getStyleClass().add("gameTitle");

        // Animations
        ViewResourceLoader.fadeIn(fadeTransitionGameMenu).setNode(startMenu);
        ViewResourceLoader.fadeIn(fadeTransitionGameTitle).setNode(gameTitle);

        // Add what you want to display
        this.getChildren().setAll(
                gameCanvas,
                abilityBar,
                scorePanel,
                startMenu,
                gameTitle,
                gameOverPanel

        );

        // Align based on window container
        setAlignment(scorePanel, Pos.TOP_RIGHT);
        setAlignment(abilityBar, Pos.BOTTOM_CENTER);
        setAlignment(gameTitle, Pos.TOP_CENTER);
        setAlignment(gameOverPanel, Pos.CENTER);

        // Align inside their respective container
        startMenu.setAlignment(Pos.BOTTOM_CENTER);
        abilityBar.setAlignment(Pos.CENTER);
        gameOverPanel.setAlignment(Pos.CENTER);


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
        ViewResourceLoader.fadeIn(fadeTransitionGameMenu).setNode(startMenu);
        ViewResourceLoader.fadeIn(fadeTransitionGameTitle).setNode(gameTitle);
        // Making all children of start menu transparent to mouse events
        startMenu.setMouseTransparent(true);
    }

    public void menuFadeOut() {
        ViewResourceLoader.fadeOut(fadeTransitionGameMenu).setNode(startMenu);
        ViewResourceLoader.fadeOut(fadeTransitionGameTitle).setNode(gameTitle);
        // Making all children of start menu visible to mouse events
        startMenu.setMouseTransparent(false);
    }

    public void showGameOver() {
        gameOverPanel.setVisible(true);
    }

    public void hideGameOver() {
        gameOverPanel.setVisible(false);
    }


}
