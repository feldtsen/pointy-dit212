package game.view.pages;

import game.view.ViewResourceLoader;
import game.view.pages.canvas.GameCanvas;
import game.view.pages.menu.StartMenu;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RootParent extends StackPane {
    GameCanvas gameCanvas;
    StartMenu startMenu;

    public RootParent(Stage primaryStage) {
        gameCanvas = new GameCanvas();
        startMenu  = new StartMenu();

        // Align the component
        startMenu.setAlignment(Pos.BOTTOM_CENTER);
        startMenu.setStyle("-fx-background-color: blue");

        // Add a stylesheet
        this.getStylesheets().add(ViewResourceLoader.stylesheet);

        // Add what you want to display
       this.getChildren().setAll(
               gameCanvas,
               startMenu
       );

        // Bind the size of different components to the window size
        gameCanvas.widthProperty().bind(primaryStage.widthProperty());
        gameCanvas.heightProperty().bind(primaryStage.heightProperty());


    }

}
