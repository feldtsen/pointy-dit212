package game.services;

import game.model.level.ILevel;

import java.util.Iterator;

public interface ILevelLoader extends Iterator<ILevel> {

    ILevel getLevel();
}
