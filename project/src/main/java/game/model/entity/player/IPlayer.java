package game.model.entity.player;

import game.model.entity.movable.ILiving;
import game.model.entity.movable.IMovable;
import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.shape2d.ICircle;
import javafx.geometry.Point2D;

import java.util.List;

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
    void addAbility(IAbility ability);

    List<IAbility> getAbilities();

    // Returns the direction which the player is facing
    Point2D getFacingDirection();

    // sets the position which the player is facing. This is usually defined using the mouse position
    void setFacingTowards(Point2D position);
}
