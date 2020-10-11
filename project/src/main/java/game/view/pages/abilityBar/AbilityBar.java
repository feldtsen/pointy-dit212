package game.view.pages.abilityBar;

import javafx.scene.layout.HBox;

public class AbilityBar extends HBox {

    public AbilityBar() {

      AbilityHolder dash = new AbilityHolder("Dash");
      AbilityHolder reflect = new AbilityHolder("Reflect");
      AbilityHolder shockwave = new AbilityHolder("Shockwave");

      this.getStyleClass().add("abilityBar");

      this.getChildren().setAll(
              dash,
              reflect,
              shockwave
      );
    }



}
