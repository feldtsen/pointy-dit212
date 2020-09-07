package game.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import game.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
