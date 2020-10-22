/*
 * Authors: Erik Magnusson
 *
 * Interface used by LevelLoader. Implements Iterator to allow for iteration over ILevel objects when changing levels.
 */

package game.services;

import game.model.level.ILevel;

import java.util.Iterator;

// Interface for LevelLoader.
public interface ILevelLoader extends Iterator<ILevel> {

    // Get the level of current level number.
    ILevel getLevel();

    // Get the selected level
    ILevel getSelectedLevel(int levelNr);
}
