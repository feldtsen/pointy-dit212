package game.view.pages.abilityBar;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AbilityBar extends HBox {

    public AbilityBar() {

      Label dash = new Label("Dash");
      Label reflect = new Label("Reflect");
      Label shockwave = new Label("Shockwave");

      this.getStyleClass().add("abilityBar");

      this.getChildren().setAll(
              dash,
              reflect,
              shockwave
      );
    }



}
