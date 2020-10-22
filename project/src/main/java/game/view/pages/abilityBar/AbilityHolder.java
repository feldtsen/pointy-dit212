/*
 * Authors: Joachim Pedersen
 *
 * Holder of a single ability cooldown indicator. UI element used by the player
 * to determine if they can use an ability or not.
 */

package game.view.pages.abilityBar;

import game.model.ability.IAbility;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AbilityHolder extends VBox {
    // Cooldown timer (label for viewing the number of seconds left until cooldown is over)
    private final Label cooldownTimer = new Label();

    // Rectangle shape for cooldown indicator
    private final javafx.scene.shape.Rectangle progress = new javafx.scene.shape.Rectangle(0, 10, Color.WHITE);
    private final Label abilityLabel = new Label();

    // name of ability
    private final String abilityName;

    public AbilityHolder (String abilityName) {
        this.abilityName = abilityName;

        // Each ability gets a name, cooldown counter and a progress bar on the GUI
        this.getChildren().setAll(
                abilityLabel,
                cooldownTimer,
                progress
        );
    }

    // Sets the data for the visual cooldown indicator
    public void setCooldown(IAbility ability) {
        if(abilityLabel.getText().equals("")) abilityLabel.setText(abilityName);

        // Reformats the data retrieved to only show one decimal
        String rounded = String.format("%.1f", ability.getCooldownCountdown());
        progress.setWidth(ability.getCooldownCountdownPercentage());

        cooldownTimer.setText(rounded);
    }


}
