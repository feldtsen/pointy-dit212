package game;

import game.model.Game;
import game.model.entity.movable.MovableEntity;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    public MovableEntity<ICircle> makeEntity(Point2D position, double radius) {
        return new MovableEntity<>(position, 5, 5, new Circle(radius)) {
        };
    }

    @Before
    public void init() {
        game = new Game();
    }

    @Test
    public void limitToBoundsNoLimit() {
        MovableEntity<ICircle> entity = makeEntity(new Point2D(100, 100), 5);
        Point2D pos = entity.getPosition();
        game.containToBounds(entity);
        assertEquals(pos, entity.getPosition());
    }

    @Test
    public void limitToBoundsLimitTopCorner() {
        MovableEntity<ICircle> entity = makeEntity(new Point2D(0, 0), 5);
        game.containToBounds(entity);

        Point2D expected = new Point2D(5, 5);
        assertEquals(expected, entity.getPosition());
    }

    @Test
    public void limitToBoundsLimitBottomCorner() {
        MovableEntity<ICircle> entity = makeEntity(new Point2D(game.currentLevel.getWidth(), game.currentLevel.getHeight()), 10);
        game.containToBounds(entity);

        Point2D expected = new Point2D(game.currentLevel.getWidth() - 10, game.currentLevel.getHeight() - 10);
        assertEquals(expected, entity.getPosition());
    }


}
