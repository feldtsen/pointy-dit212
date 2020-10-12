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
    private final static double INITIAL_WIDTH = Screen.getPrimary().getBounds().getWidth() * .7;
    private final static double INITIAL_HEIGHT = INITIAL_WIDTH * WIDTH_TO_HEIGHT_RATIO;
    private final static double MIN_SIZE = 800;

    @Override
    public void start(Stage primaryStage) {
        // Initialize with the correct aspect ratio, and bind the relation between the width and the height
        primaryStage.minHeightProperty().bind(primaryStage.widthProperty().multiply(WIDTH_TO_HEIGHT_RATIO));
        primaryStage.maxHeightProperty().bind(primaryStage.widthProperty().multiply(WIDTH_TO_HEIGHT_RATIO));

        // Set constraint to the minimum allowed size
        primaryStage.setMinWidth(MIN_SIZE);

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        // To begin with, occupy 70% of the width
        primaryStage.setWidth(INITIAL_WIDTH);
        // Used to force the position of the window to be centered
        primaryStage.setX(screenWidth / 2 - INITIAL_WIDTH / 2);
        primaryStage.setY(screenHeight / 2 - INITIAL_HEIGHT / 2);

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