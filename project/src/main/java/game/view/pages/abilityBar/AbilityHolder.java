package game.view.pages.abilityBar;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AbilityHolder extends VBox {
    private final Label cooldownTimer = new Label();

    public AbilityHolder (String abilityName) {
        Label abilityLabel = new Label(abilityName);

        this.getChildren().setAll(
                abilityLabel,
                cooldownTimer
        );
    }

    public void setCooldown(double cooldown) {
       cooldownTimer.setText(cooldown+"");
    }
}
