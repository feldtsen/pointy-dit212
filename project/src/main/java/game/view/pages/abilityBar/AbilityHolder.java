/*
 * Authors: Joachim Pedersen
 */

package game.view.pages.abilityBar;

import game.model.ability.IAbility;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AbilityHolder extends VBox {
    private final Label cooldownTimer = new Label();
    private final javafx.scene.shape.Rectangle progress = new javafx.scene.shape.Rectangle(0, 10, Color.WHITE);
    Label abilityLabel = new Label();

    String abilityName;

    public AbilityHolder (String abilityName) {
        this.abilityName = abilityName;

        this.getChildren().setAll(
                abilityLabel,
                cooldownTimer,
                progress
        );
    }

    public void setCooldown(IAbility ability) {
        if(abilityLabel.getText().equals("")) abilityLabel.setText(abilityName);
        String rounded = String.format("%.1f", ability.getCooldownCountdown());
        progress.setWidth(ability.getCooldownCountdownPercentage());
        cooldownTimer.setText(rounded);
    }


}
