package game.services;

import game.model.level.ILevel;

import java.util.Iterator;

// Interface for LevelLoader. Implements Iterator to allow for iteration over ILevel objects when changing levels.
public interface ILevelLoader extends Iterator<ILevel> {

    // Get the level of current level number.
    ILevel getLevel();

    // Get the selected level
    void setLevel(int levelNr);
}
