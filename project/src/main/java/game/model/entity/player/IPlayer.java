package game.model.entity.player;

import game.model.ILiving;
import game.model.IMovable;
import game.model.entity.IEntity;
import game.model.shape2d.ICircle;
import game.model.shape2d.IShape2D;

public interface IPlayer extends IEntity<ICircle>, ILiving, IMovable {
    boolean activateAbility();
    void moveUp();
    void moveLeft();
    void moveDown();
    void moveRight();
}
