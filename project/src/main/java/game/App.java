package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// Top level class for initializing the application
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the game window using the fxml loader and create a new scene
        Scene scene = new Scene(loadFXML("gamewindow"));

        // Set the scene to the main stage (window)
        stage.setScene(scene);

        // Display the stage (window)
        stage.show();
    }

    // Load an fxml file
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // Launch the application
    public static void main(String[] args) {
        launch();
    }
}