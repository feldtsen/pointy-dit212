/*
 * Authors: Joachim Pedersen
 *
 * Simple implementation of the responsive button. Used to create
 * an exit button, for quitting the game.
 */

package game.view.pages.menu.buttons;

public class ExitButton extends ResponsiveButton {
   public ExitButton(String text)  {
      this.setText(text);
   }
}
