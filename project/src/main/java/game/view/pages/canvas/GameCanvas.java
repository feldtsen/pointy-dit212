/*
 * Authors: Joachim Pedersen
 *
 * Canvas class used to draw entities and effects to the screen.
 * Overrides certain canvas methods to achieve the required behavior, specifically
 * to force the correct ratio of the window.
 */

package game.view.pages.canvas;

import javafx.scene.canvas.Canvas;

public class GameCanvas extends Canvas {
    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return this.getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return this.getHeight();
    }
}
