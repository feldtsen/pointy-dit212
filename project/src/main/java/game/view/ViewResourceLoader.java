package game.view;

import game.App;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

// Used to ensure we only load resources once
public class ViewResourceLoader {
    public final static String stylesheet = App.class.getResource("/styles/style.css").toString();

    public static FadeTransition fadeIn(FadeTransition fadeTransition) {
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.playFromStart();
        return fadeTransition;
    }
    public static FadeTransition fadeOut(FadeTransition fadeTransition) {
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();
        return fadeTransition;
    }
}
