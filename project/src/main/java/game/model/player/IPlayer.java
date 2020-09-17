package game.model.player;

import game.model.shape2d.IShape2D;

public interface IPlayer<T extends IShape2D> {
    boolean activateAbility();
    void moveUp();
    void moveLeft();
    void moveDown();
    void moveRight();
}
