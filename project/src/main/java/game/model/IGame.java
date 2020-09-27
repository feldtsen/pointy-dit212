package game.model;

import game.model.ability.action.IAbilityAction;
import game.model.level.ILevel;

import java.util.List;

// Interface for class running the game itself.
// Does not include the gameLoop, which should be handled by a controller.
public interface IGame extends IUpdatable {

    // This method returns the abilities (in the form of ability actions) which is currently active.
    List<IAbilityAction> getActiveAbilityActions();

    boolean setLevel(ILevel level);

    int getScore();
    ILevel getCurrentLevel();
    List<ILevel> getLevels();
}
