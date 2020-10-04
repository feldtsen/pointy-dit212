package game.view.pages;

import game.controller.GameWindowController;
import game.view.ViewResourceLoader;
import game.view.pages.canvas.GameCanvas;
import game.view.pages.menu.StartMenu;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends StackPane {
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
        this.getStyleClass().add("mainWindow");

        // Add what you want to display
       this.getChildren().setAll(
               gameCanvas,
               startMenu
       );

        // Bind the size of different components to the window size
        gameCanvas.widthProperty().bind(this.widthProperty());
        gameCanvas.heightProperty().bind(this.heightProperty());


    }

    public GameCanvas getGameCanvas () {
        return gameCanvas;
    }

    public void hideMenu() {
        startMenu.toBack();
    }

    public void showMenu() {
        startMenu.toFront();
    }

}
