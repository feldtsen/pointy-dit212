package game;

import game.controller.GameWindowController;
import game.view.pages.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

// Top level class for initializing the application
public class App extends Application {
    private final static double WIDTH_TO_HEIGHT_RATIO = .5625; // 16:9 (reversed)
    private final static double INITIAL_WIDTH = 1200;
    private final static double INITIAL_HEIGHT = INITIAL_WIDTH * WIDTH_TO_HEIGHT_RATIO;
    private final static double MIN_SIZE = 800;

    @Override
    public void start(Stage primaryStage) {
        // Initialize with the correct aspect ratio, and bind the relation between the width and the height
        primaryStage.minHeightProperty().bind(primaryStage.widthProperty().multiply(WIDTH_TO_HEIGHT_RATIO));
        primaryStage.maxHeightProperty().bind(primaryStage.widthProperty().multiply(WIDTH_TO_HEIGHT_RATIO));

        // Get screen bound
        System.out.println(Screen.getPrimary().getBounds().getWidth());

        // Set constraint to the minimum allowed size
        primaryStage.setMinWidth(MIN_SIZE);

        // Start bordered fullscreen
        primaryStage.setWidth(Screen.getPrimary().getBounds().getWidth());

        //TODO: is there a better way?
        GameWindowController gameWindowController = new GameWindowController();

        // Load game window
        Scene scene = new Scene(gameWindowController.getWindow());
        // Set the scene to the main stage (window)
        primaryStage.setScene(scene);
        // Display the stage (window)
        primaryStage.show();
    }

    // Launch the application
    public static void main(String[] args) {
        launch();
    }
}