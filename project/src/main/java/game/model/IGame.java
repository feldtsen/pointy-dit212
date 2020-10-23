/*
 * Authors: Anton Hildingsson, Erik Magnusson, Mattias Oom, Joacchim Pedersen
 *
 * Interface for class running the game itself. Does not include the gameLoop, 
 * which should be handled by a controller.
 *
 * The interface defines a set of functions which can be called from controllers
 * to influence the game state and player actions. For example, the next level
 * is not loaded instantly when the player finishes one, since the controller
 * might want to perform an action inbetween. Instead, the controller has to call 
 * nextLevel to load the next level. 
 *
 * The implementing class will be the root model class, and handles all gameplay.
 * When t he update method is called, the game state should progress.
 */

package game.model;

import game.controller.event.IAbilityActionEventHandler;
import game.model.ability.action.IAbilityAction;
import game.model.level.ILevel;

import java.util.List;

public interface IGame extends IAbilityActionEventHandler, IUpdatable {
    // Activate player ability
    boolean activatePlayerAbility(int index);

    // This method returns the abilities (in the form of ability actions) which is currently active.
    List<IAbilityAction> getActiveAbilityActions();

    // This method returns the times for which an ability action has been active
    List<Double> getActiveAbilityTimes();

    boolean setLevel(ILevel level);

    // Given a level, load that level
    void changeLevel(int levelNr);

    // Checks if the given level exist
    boolean levelExist(int level);

    // Retrieves the game time in String format (time used on the current level)
    double getTime();

    // Sets the time back to 0
    void resetTimer();

    // This method returns true on player death.
    boolean isGameOver();

    // Returns true when game is completed.
    boolean isGameWin();

    // Return true if you have killed all the enemies
    boolean isNextLevel();

    // Loads the next level
    void nextLevel();

    // Restarts the current level.
    void restartLevel();

    ILevel getCurrentLevel();
}
