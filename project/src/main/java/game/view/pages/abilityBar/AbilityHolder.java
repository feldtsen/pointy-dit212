package game.view.pages.abilityBar;

import game.model.ability.IAbility;
import game.model.shape2d.Rectangle;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AbilityHolder extends VBox {
    private final Label cooldownTimer = new Label();
    private final javafx.scene.shape.Rectangle progress = new javafx.scene.shape.Rectangle(0, 10, Color.WHITE);

    public AbilityHolder (String abilityName) {
        Label abilityLabel = new Label(abilityName);

        this.getChildren().setAll(
                abilityLabel,
                cooldownTimer,
                progress
        );
    }

    public void setCooldown(IAbility ability) {
        String rounded = String.format("%.1f", ability.getCooldownCountdown());
        double relativeProgressProgression = 0;
        progress.setWidth(ability.getCooldownCountdownPercentage());
        cooldownTimer.setText(rounded);
    }


}
