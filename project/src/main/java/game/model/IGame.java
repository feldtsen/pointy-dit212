/*
 * Authors: Anton Hildingsson, Erik Magnusson, Mattias Oom, Joacchim Pedersen
 */

package game.model;

import game.controller.event.AbilityActionEventHandler;
import game.model.ability.action.IAbilityAction;
import game.model.level.ILevel;
import javafx.geometry.Point2D;

import java.util.List;

// Interface for class running the game itself.
// Does not include the gameLoop, which should be handled by a controller.
public interface IGame extends AbilityActionEventHandler, IUpdatable {
    // Activate player ability
    boolean activatePlayerAbility(int index);

    // Set player facing direction
    //void setPlayerFacingPosition(Point2D playerFacingPosition);

    // This method returns the abilities (in the form of ability actions) which is currently active.
    List<IAbilityAction> getActiveAbilityActions();

    // This method returns the times for which an ability action has been active
    List<Double> getActiveAbilityTimes();

    boolean setLevel(ILevel level);

    // Retrieves the game time in String format (time used on the current level)
    double getTime();

    // Sets the time back to 0
    void resetTimer();

    // This method returns true on player death.
    boolean isGameOver();

    // Returns true when game is completed.
    boolean isGameWin();

    // Loads the next level
    void nextLevel();

    // Restarts the current level.
    void restartLevel();

    ILevel getCurrentLevel();
}
