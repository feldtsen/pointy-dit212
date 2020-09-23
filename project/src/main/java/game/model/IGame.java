package game.model;

import game.model.level.ILevel;

import java.util.List;

public interface IGame {
    ILevel getCurrentLevel();
    List<ILevel> getLevels();
    int getScore();

    boolean setLevel(ILevel level);
}
