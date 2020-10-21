/*
 * Authors: Joachim Pedersen, Simon Genne, Erik Magnusson
 */

package game.view.pages;

import game.controller.GameWindowController;
import game.view.ViewResourceLoader;
import game.view.pages.abilityBar.AbilityBar;
import game.view.pages.canvas.GameCanvas;
import game.view.pages.gameState.GameOverPanel;
import game.view.pages.menu.StartMenu;
import game.view.pages.score.ScorePanel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MainWindow extends StackPane {
    private static final String MAIN_WINDOW_CSS = "mainWindow";
    private static final String GAME_TITLE = "J E R K  E V E R T";


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

        // Enables key presses to get captured at every graphical layer
        gameCanvas.setFocusTraversable(true);

    }

    public GameCanvas getGameCanvas () {
        return gameCanvas;
    }

    public ScorePanel getScorePanel () {return scorePanel;}

    public AbilityBar getAbilityBar () {
        return abilityBar;
    }

    public void showMenu() {
        startMenu.setVisible(true);
        gameTitle.setVisible(true);
    }

    public void hideMenu() {
        startMenu.setVisible(false);
        gameTitle.setVisible(false);
    }

    // Display game over message and hide other UI-elements
    public void showGameOver() {
        startMenu.setVisible(false);
        gameOverPanel.setVisible(true);
    }

    // Remove game over message
    public void hideGameOver() {
        gameOverPanel.setVisible(false);
    }

    // Makes abilityBar and scorePanel visible
    public void showUI() {
        abilityBar.setVisible(true);
        scorePanel.setVisible(true);
    }

    public void hideUI() {
        abilityBar.setVisible(false);
        scorePanel.setVisible(false);
    }
}
