package game.model;

import game.model.ability.action.IAbilityAction;
import game.model.level.ILevel;

import java.util.List;

public interface IGame extends IUpdatable {
    ILevel getCurrentLevel();
    List<ILevel> getLevels();
    List<IAbilityAction> activeAbilityActions();

    int getScore();

    boolean setLevel(ILevel level);
}
