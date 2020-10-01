package game.model.entity.player;

import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.entity.movable.LivingEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Player extends LivingEntity<ICircle> implements IPlayer {
    // Unit vectors in all four directions. These are used as helper vectors
    // for defining different movement actions.
    private final static Point2D LEFT  = new Point2D(-1, 0);
    private final static Point2D RIGHT = new Point2D(1, 0);
    private final static Point2D UP    = new Point2D(0, -1);
    private final static Point2D DOWN  = new Point2D(0, 1);

    private final ArrayList<IAbility> abilities;

    public Player(Point2D position, double radius, double responsiveness, double maxSpeed, int strength) {
        super(position, responsiveness, maxSpeed, 1, new Circle(radius), strength);
        this.abilities = new ArrayList<>();
    }

    @Override
    public IAbilityAction activateAbility(int index) {
        return abilities.get(index).use(this, null);
    }

    @Override
    public void moveUp() {
        moveDirection(UP);
    }

    @Override
    public void moveLeft() {
        moveDirection(LEFT);
    }

    @Override
    public void moveDown() {
        moveDirection(DOWN);
    }

    @Override
    public void moveRight() {
        moveDirection(RIGHT);
    }

    @Override
    public boolean addAbility(IAbility ability) {
        abilities.add(ability);
        return true;
    }

    private void moveDirection(Point2D direction) {
        // Adds a force in the direction of movement. The vector is multiplied to reach the
        // length of max force, which will be the movement acceleration of the player.
       addForce(direction.multiply(getMaxForce()));
    }
}
