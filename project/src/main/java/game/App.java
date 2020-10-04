package game;

import game.view.pages.RootParent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Top level class for initializing the application
public class App extends Application {
    private final static double WIDTH_TO_HEIGHT_RATIO = .5625; // 16:9 (reversed)
    private final static double INITIAL_WIDTH = 1200;
    private final static double INITIAL_HEIGHT = INITIAL_WIDTH * WIDTH_TO_HEIGHT_RATIO;
    private final static double MIN_SIZE = 800;

    @Override
    public void start(Stage primaryStage) {
        // Load game window
        Scene scene = new Scene(new RootParent(primaryStage));

        // Set the scene to the main stage (window)
        primaryStage.setScene(scene);

        // Initialize with the correct aspect ratio, and bind the relation between the width and the height
        primaryStage.setHeight(INITIAL_HEIGHT); // this will also be set when minimizing a maximized screen
        primaryStage.setWidth(INITIAL_WIDTH);
        primaryStage.minHeightProperty().bind(primaryStage.widthProperty().multiply(WIDTH_TO_HEIGHT_RATIO));
        primaryStage.maxHeightProperty().bind(primaryStage.widthProperty().multiply(WIDTH_TO_HEIGHT_RATIO));

        // As default maximize screen
        primaryStage.setMaximized(true);

        // Set constraint to the minimum allowed size
        primaryStage.setMinWidth(MIN_SIZE);

        // Display the stage (window)
        primaryStage.show();
    }

    // Launch the application
    public static void main(String[] args) {
        launch();
    }
}