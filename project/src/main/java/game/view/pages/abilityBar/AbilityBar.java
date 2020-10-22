/*
 * Authors: Joachim Pedersen
 *
 * A UI element which holds ability cooldown indicators for the player abilities.
 * Hard coded to the three player abilities: dash, reflect and shockwave.
 */

package game.view.pages.abilityBar;

import game.model.ability.IAbility;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.List;

public class AbilityBar extends HBox {
    // Holders for different ability cooldown indicators
    private final AbilityHolder dash;
    private final AbilityHolder reflect;
    private final AbilityHolder shockwave;

    public AbilityBar() {
      dash = new AbilityHolder("Dash");
      reflect = new AbilityHolder("Reflect");
      shockwave = new AbilityHolder("Shockwave");

      // Set holders to alight to the center of the screen
      dash.setAlignment(Pos.CENTER);
      reflect.setAlignment(Pos.CENTER);
      shockwave.setAlignment(Pos.CENTER);

      // set styles
      this.getStyleClass().add("abilityBar");
      dash.getStyleClass().add("ability");
      reflect.getStyleClass().add("ability");
      shockwave.getStyleClass().add("ability");

      this.getChildren().setAll(
              dash,
              shockwave,
              reflect
      );
    }

    // updates the state of the ability cooldown indicators
    public void updateAbilities(List<IAbility> abilities) {
        // 0 is dash
        dash.setCooldown(abilities.get(0));

        // 1 is shockwave
        shockwave.setCooldown(abilities.get(1));

        // 2 is reflect
        reflect.setCooldown(abilities.get(2));
    }



}
