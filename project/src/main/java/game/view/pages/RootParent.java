package game.view.pages;

import game.view.pages.canvas.GameCanvas;
import game.view.pages.menu.StartMenu;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RootParent extends StackPane {

    public RootParent(Stage primaryStage) {
        GameCanvas gameCanvas = new GameCanvas();
        StartMenu startMenu   = new StartMenu();

        // Bind the size of different components to the window size
        // TODO: implement this

        // Add what you want to display
       this.getChildren().setAll(
               gameCanvas,
               startMenu
       );

    }

}
