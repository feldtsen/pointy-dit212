module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens game.controller to javafx.fxml;
    exports game;
}