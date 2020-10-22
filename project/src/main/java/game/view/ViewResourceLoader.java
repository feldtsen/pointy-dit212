/*
 * Authors: Joachim Pedersen
 */

package game.view;

import game.App;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

// Used to ensure we only load resources once
public class ViewResourceLoader {
    public final static String stylesheet = App.class.getResource("/styles/style.css").toString();
    public final static double INITIAL_WIDTH = 1200;
    public final static double WIDTH_TO_HEIGHT_RATIO = .5625; // 16:9 (reversed)
    public final static double INITIAL_HEIGHT = INITIAL_WIDTH * WIDTH_TO_HEIGHT_RATIO;
}
