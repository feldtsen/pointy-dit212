/*
 * Authors: Joachim Pedersen
 *
 * Class for loading resources used by view components.
 * Used to ensure we only load resources once.
 */

package game.view;

import game.App;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class ViewResourceLoader {
    // The stylesheet defines styles for buttons and other UI elements
    public final static String stylesheet = App.class.getResource("/styles/style.css").toString();

    // Defines initial width of window
    public final static double INITIAL_WIDTH = 1200;

    // Defines the ratio of the window
    public final static double WIDTH_TO_HEIGHT_RATIO = .5625; // 16:9 (reversed)

    // Calculate and set the height of the window, based on the ratio
    public final static double INITIAL_HEIGHT = INITIAL_WIDTH * WIDTH_TO_HEIGHT_RATIO;

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
