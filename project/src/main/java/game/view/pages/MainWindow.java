package game.view.pages;

import game.controller.GameWindowController;
import game.view.ViewResourceLoader;
import game.view.pages.canvas.GameCanvas;
import game.view.pages.menu.StartMenu;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MainWindow extends StackPane {
    private static final String MAIN_WINDOW_CSS = "mainWindow";
    FadeTransition startMenuFade = new FadeTransition(new Duration(200));

    GameCanvas gameCanvas;
    StartMenu startMenu;

    public MainWindow(GameWindowController gameWindowController) {
        gameCanvas = new GameCanvas();
        startMenu  = new StartMenu(gameWindowController);

        // Align the component
        startMenu.setAlignment(Pos.BOTTOM_CENTER);

        // Add a stylesheet
        this.getStylesheets().add(ViewResourceLoader.stylesheet);

        // Add class for styling
        this.getStyleClass().add(MAIN_WINDOW_CSS);

        // Animations
        startMenuFade.setNode(startMenu);

        // Add what you want to display
       this.getChildren().setAll(
               gameCanvas,
               startMenu
       );

        // Bind the size of different components to the window size, making the components responsive
        // (relative to its parent)
        gameCanvas.widthProperty().bind(this.widthProperty());
        gameCanvas.heightProperty().bind(this.heightProperty());


    }

    public GameCanvas getGameCanvas () {
        return gameCanvas;
    }

    public void hideMenu() {
        startMenuFade.setFromValue(1);
        startMenuFade.setToValue(0);
        startMenuFade.playFromStart();
        // Making all children of start menu transparent to mouse events
        startMenu.setMouseTransparent(true);
    }

    public void showMenu() {
        startMenuFade.setFromValue(0);
        startMenuFade.setToValue(1);
        startMenuFade.playFromStart();
        // Making all children of start menu visible to mouse events
        startMenu.setMouseTransparent(false);
    }

}
