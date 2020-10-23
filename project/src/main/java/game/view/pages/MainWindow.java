/*
 * Authors: Joachim Pedersen, Simon Genne, Erik Magnusson
 *
 * The main window of the game. Sets up a window, a canvas, and
 * various UI elements.
 *
 */

package game.view.pages;

import game.controller.GameWindowController;
import game.view.ViewResourceLoader;
import game.view.pages.abilityBar.AbilityBar;
import game.view.pages.canvas.GameCanvas;
import game.view.pages.gameState.GameStatePanel;
import game.view.pages.level.LevelPanel;
import game.view.pages.menu.StartMenu;
import game.view.pages.score.HighscorePanel;
import game.view.pages.score.ScorePanel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainWindow extends StackPane {
    private static final String MAIN_WINDOW_CSS = "mainWindow";
    private static final String GAME_TITLE = "J E R K  E V E R T";

    // The canvas which is used to draw shapes and effects to the screen
    private final GameCanvas gameCanvas;

    // The UI start menu which the user interacts with
    private final StartMenu startMenu;

    // A panel displaying the current store
    private final ScorePanel scorePanel;

    // The ability cooldown indicators
    private final AbilityBar abilityBar;

    // The title of the game
    private final Label gameTitle;

    // A panel for displaying the current state of the game
    private GameStatePanel gameStatePanel;

    private final HighscorePanel highscorePanel;

    private final LevelPanel levelPanel;

    private final ScrollPane scrollPane;

    public MainWindow(GameWindowController gameWindowController) {
        gameCanvas = new GameCanvas();
        startMenu  = new StartMenu(gameWindowController);
        highscorePanel = new HighscorePanel();
        scorePanel = new ScorePanel();
        abilityBar = new AbilityBar();
        gameTitle = new Label(GAME_TITLE);
        gameStatePanel = new GameStatePanel();
        levelPanel = new LevelPanel();
        scrollPane = new ScrollPane();

        // Add a stylesheet
        this.getStylesheets().add(ViewResourceLoader.stylesheet);

        // Add class for styling
        this.getStyleClass().add(MAIN_WINDOW_CSS);
        gameTitle.getStyleClass().add("gameTitle");
        scrollPane.getStyleClass().add("menuContent");
        scrollPane.setFitToWidth(true);

        windowSetup();
    }

    private void windowSetup() {

        // Add what you want to display
        this.getChildren().setAll(
                gameCanvas,
                abilityBar,
                scorePanel,
                startMenu,
                gameTitle,
                gameStatePanel,
                scrollPane
        );

        scrollPane.setVisible(false);

        // Align based on window container
        setAlignment(scorePanel, Pos.TOP_RIGHT);
        setAlignment(abilityBar, Pos.BOTTOM_CENTER);
        setAlignment(gameTitle, Pos.TOP_CENTER);
        setAlignment(gameStatePanel, Pos.CENTER);
        setAlignment(levelPanel, Pos.TOP_CENTER);
        setAlignment(scrollPane, Pos.TOP_CENTER);
        setAlignment(startMenu, Pos.BOTTOM_CENTER);

        // Align inside their respective container
        startMenu.setAlignment(Pos.BOTTOM_CENTER);
        abilityBar.setAlignment(Pos.CENTER);
        gameStatePanel.setAlignment(Pos.CENTER);


        // Bind the size of different components to the window size, making the components responsive
        // (relative to its parent)
        gameCanvas.widthProperty().bind(this.widthProperty());
        gameCanvas.heightProperty().bind(this.heightProperty());

        scrollPane.prefWidthProperty().bind(this.widthProperty());

        levelPanel.prefWidthProperty().bind(this.widthProperty());
        levelPanel.setAlignment(Pos.CENTER);

        highscorePanel.prefWidthProperty().bind(this.widthProperty());
        highscorePanel.setAlignment(Pos.CENTER);

        scrollPane.maxHeightProperty().bind(this.heightProperty().multiply(.8));
        startMenu.maxHeightProperty().bind(this.heightProperty().multiply(.2));

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


    public void addHighscore (String level, double time) {
        highscorePanel.createScoreEntry(level, time);
    }

    public void showHighscores () {
        gameTitle.setVisible(false);

        scrollPane.setContent(highscorePanel);
        scrollPane.setVisible(true);

        showGameTitle();
    }


    public void showLevelPanel () {
        gameTitle.setVisible(false);

        scrollPane.setContent(levelPanel);
        scrollPane.setVisible(true);

        showGameTitle();
    }


    public void addLevelButton(Button load) {
        levelPanel.createEntry(load);
    }

    // Sets menus to visible
    public void showMenu() {
        startMenu.setVisible(true);
        gameTitle.setVisible(true);
    }

    // Hides menus
    public void hideMenu() {
        scrollPane.setVisible(false);
        startMenu.setVisible(false);
        gameTitle.setVisible(false);
    }

    public void clearHighscorePanel() {
       highscorePanel.getChildren().clear();
    }
    public void clearLevelPanel() {
        levelPanel.getChildren().clear();
    }

    private void showGameTitle() {
        if (!scrollPane.isVisible()) gameTitle.setVisible(true);
    }

    // Changes the game state
    public void setGameState(String gameStateMessage, String gameStateInstructions) {
        this.gameStatePanel = new GameStatePanel(gameStateMessage, gameStateInstructions);
        windowSetup();
    }

    // Display game over message and hide other UI-elements
    public void showGameState() {
        startMenu.setVisible(false);
        gameStatePanel.setVisible(true);
    }

    // Remove game over message
    public void hideGameState() {
        gameStatePanel.setVisible(false);
    }

    // Makes abilityBar and scorePanel visible
    public void showUI() {
        abilityBar.setVisible(true);
        scorePanel.setVisible(true);
    }

    // Hides UI elements
    public void hideUI() {
        abilityBar.setVisible(false);
        scorePanel.setVisible(false);
    }
}
