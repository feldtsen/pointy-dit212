package game.view.pages.abilityBar;

import game.model.ability.IAbility;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.List;

public class AbilityBar extends HBox {
    AbilityHolder dash;
    AbilityHolder reflect;
    AbilityHolder shockwave;

    public AbilityBar() {

      dash = new AbilityHolder("Dash");
      reflect = new AbilityHolder("Reflect");
      shockwave = new AbilityHolder("Shockwave");

      dash.setAlignment(Pos.CENTER);
      reflect.setAlignment(Pos.CENTER);
      shockwave.setAlignment(Pos.CENTER);

      this.getStyleClass().add("abilityBar");

      this.getChildren().setAll(
              dash,
              shockwave,
              reflect
      );
    }

    public void updateAbilities(List<IAbility> abilities) {
        // 0 is dash
        dash.setCooldown(abilities.get(0).getCooldownCountdown());

        // 1 is shockwave
        shockwave.setCooldown(abilities.get(1).getCooldownCountdown());

        // 2 is reflect
        reflect.setCooldown(abilities.get(2).getCooldownCountdown());
    }



}
