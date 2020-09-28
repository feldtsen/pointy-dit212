module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires gson;

    opens game.controller to javafx.fxml;
    exports game;
}