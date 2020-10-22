/*
 * Authors: Joachim Pedersen
 *
 * Class for defining a button which size is responsive, i.e adapts to the size of the screen.
 */

package game.view.pages.menu.buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public abstract class ResponsiveButton extends Button {
    // Improbably large number, used to make sure the button size is responsive (adapts to screen size).
    private final static double NUMBER_BIGGER_THAN_A_MONITORS_PIXEL_RATIO =  1000000000;
    ResponsiveButton () {
        // When a button inside an HBox container has a width that exceeds its given space, it will only
        // take the available space and split the space evenly if its done on every button.
        this.setMaxWidth(NUMBER_BIGGER_THAN_A_MONITORS_PIXEL_RATIO);
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}
