package game.model.entity.player;

import game.model.entity.movable.ILiving;
import game.model.entity.movable.IMovable;
import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.shape2d.ICircle;

public interface IPlayer extends ILiving, IMovable<ICircle> {
    IAbilityAction activateAbility(int index);
    void moveUp();
    void moveLeft();
    void moveDown();
    void moveRight();
    boolean addAbility(IAbility ability);
}
