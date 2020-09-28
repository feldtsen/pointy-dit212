package game.model.entity.player;

import game.model.ILiving;
import game.model.IMovable;
import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.shape2d.ICircle;

public interface IPlayer extends IEntity<ICircle>, ILiving, IMovable {
    IAbilityAction activateAbility(int index);
    void moveUp();
    void moveLeft();
    void moveDown();
    void moveRight();
    boolean addAbility(IAbility ability);
}
