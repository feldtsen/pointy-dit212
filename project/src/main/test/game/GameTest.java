package game;

import game.model.Game;
import game.model.entity.enemy.Enemy;
import game.model.entity.movable.MovableEntity;
import game.model.entity.player.Player;
import game.model.level.ILevel;
import game.model.level.Level;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.services.EntityFactory;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        MovableEntity<ICircle> entity = makeEntity(new Point2D(game.getCurrentLevel().getWidth(), game.getCurrentLevel().getHeight()), 10);
        game.containToBounds(entity);

        Point2D expected = new Point2D(game.getCurrentLevel().getWidth() - 10, game.getCurrentLevel().getHeight() - 10);
        assertEquals(expected, entity.getPosition());
    }

    @Test
    public void testUpdatePlayerLessStrength(){
        Player player = EntityFactory.basicPlayer(500, 500);

        List<Enemy> enemies= new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500,500, player,5);
        enemies.add(e1);

        ILevel level = new Level(enemies, new ArrayList<>(), new ArrayList<>(), player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);

        Game game = new Game(levels);

        game.update(1.0/60, 1);

        assertEquals(0, player.getHitPoints() );

    }
    @Test
    public void testUpdatePlayerSameStrength(){
        Player player = EntityFactory.basicPlayer(500, 500);

        List<Enemy> enemies= new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500,500, player,0);
        enemies.add(e1);

        ILevel level = new Level(enemies, new ArrayList<>(), new ArrayList<>(), player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);

        Game game = new Game(levels);

        game.update(1.0/60, 1);

        assertEquals(1, player.getHitPoints() );

    }

    @Test
    public void testUpdatePlayerMoreStrength(){
        Player player = EntityFactory.basicPlayer(500, 500);

        List<Enemy> enemies= new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500,500, player,-2);
        enemies.add(e1);

        ILevel level = new Level(enemies, new ArrayList<>(), new ArrayList<>(), player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);

        Game game = new Game(levels);

        game.update(1.0/60, 1);

        assertEquals(1, player.getHitPoints() );

    }

}
