package game.model.entity.player;

import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.entity.movable.LivingEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.util.Utils;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

// Basic player implementation
public class Player extends LivingEntity<ICircle> implements IPlayer {
    // Unit vectors in all four directions. These are used as helper vectors
    // for defining different movement actions.
    private final static Point2D LEFT  = new Point2D(-1, 0);
    private final static Point2D RIGHT = new Point2D(1, 0);
    private final static Point2D UP    = new Point2D(0, -1);
    private final static Point2D DOWN  = new Point2D(0, 1);

    // The list of player abilities. These are activated by index using the activateAbility method
    private final List<IAbility> abilities;

    // The direction which the player is facing
    private Point2D facingDirection;

    public Player(Point2D position, double radius, double responsiveness, double maxSpeed, int strength) {
        super(position, responsiveness, maxSpeed, 1, new Circle(radius), strength);
        this.abilities = new ArrayList<>();
        this.facingDirection = new Point2D(0,0);
    }

    // Activates an ability at a certain index. If there's no ability at the index, null is returned
    @Override
    public IAbilityAction activateAbility(int index) {
        if(index >= abilities.size()) return null;
        return abilities.get(index).use(this, null);
    }

    // Adds an ability to list of abilities
    @Override
    public void addAbility(IAbility ability) {
        abilities.add(ability);
    }

    // Methods for defining player movement
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

    // Helper method for moving the player in a certain direction
    private void moveDirection(Point2D direction) {
        // Adds a force in the direction of movement. The vector is multiplied to reach the
        // length of max force, which will be the movement acceleration of the player.
       addForce(direction.multiply(getMaxForce()));
    }

    // Returns the players set of abilities
    @Override
    public List<IAbility> getAbilities() {
        return abilities;
    }

    // Returns the direction which the player is facing
    @Override
    public Point2D getFacingDirection() {
        return facingDirection;
    }

    // Sets the position which the player is facing towards
    @Override
    public void setFacingTowards(Point2D position) {
        // If null, do nothing
        if (position == null) return;
        // Calculate the new facing direction.
        this.facingDirection = position.subtract(getPosition());

        // Calculate angle which represents the facing direction in radians
        double angle = Utils.heading(facingDirection);

        // Rotate the player shape to point towards the player facing position
        this.getShape().setRotation(angle);
    }
}
