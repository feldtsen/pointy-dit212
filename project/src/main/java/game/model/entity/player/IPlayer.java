package game.model.entity.player;

import game.model.entity.movable.ILiving;
import game.model.entity.movable.IMovable;
import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.shape2d.ICircle;

// Interface defining the actions and abilities of the player.
public interface IPlayer extends ILiving, IMovable<ICircle> {
    // Activates an ability at a particular index. A player might have multiple abilities.
    IAbilityAction activateAbility(int index);

    // Methods defining how a player moves across the screen
    void moveUp();
    void moveLeft();
    void moveDown();
    void moveRight();

    // Adds an ability to the player list of abilities
    boolean addAbility(IAbility ability);
}
