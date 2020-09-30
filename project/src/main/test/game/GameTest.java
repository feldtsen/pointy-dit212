package game;

import game.controller.gameLoop.GameLoop;
import game.model.Game;
import game.model.ability.Ability;
import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.AbilityBehaviour;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.movable.MovableEntity;
import game.model.entity.player.Player;
import game.model.level.ILevel;
import game.model.level.Level;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.services.EntityFactory;
import game.util.Utils;
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

        List<IEnemy> enemies= new ArrayList<>();
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

        List<IEnemy> enemies= new ArrayList<>();
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

        List<IEnemy> enemies= new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500,500, player,-2);
        enemies.add(e1);

        ILevel level = new Level(enemies, new ArrayList<>(), new ArrayList<>(), player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);

        Game game = new Game(levels);

        game.update(1.0/60, 1);

        assertEquals(1, player.getHitPoints() );

    }


    @Test
    public void testAbilityAddedToAbilityActionList() {
        Player player = EntityFactory.basicPlayer(500, 500);

        List<IEnemy> enemies= new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500,500, player,-2);

        List<IAbility> abilities = new ArrayList<>();
        abilities.add(new Ability(1) {
            @Override
            public IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
                return new IAbilityAction() {
                    @Override
                    public double getDuration() {
                        return 0;
                    }

                    @Override
                    public void apply(ILevel level, double timePassed) {
                    }
                };
            }
        });

        e1.setAbilityBehaviour(new AbilityBehaviour(abilities) {
            @Override
            public IAbilityAction apply(IEntity<?> user, IEntity<?> target) {
                return abilities.get(0).use(user, target);
            }
        });
        enemies.add(e1);

        ILevel level = new Level(enemies, new ArrayList<>(), new ArrayList<>(), player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);

        Game game = new Game(levels);
        game.update(1, 1);

        assertEquals(1, game.getActiveAbilityActions().size());
    }


    @Test
    public void testAbilityRemovedFromAbilityActionList() {
        Player player = EntityFactory.basicPlayer(500, 500);

        List<IEnemy> enemies= new ArrayList<>();
        Enemy e1 = EntityFactory.basicEnemy(500,500, player,-2);
        double duration = 0.5;

        List<IAbility> abilities = new ArrayList<>();
        abilities.add(new Ability(1) {
            @Override
            public IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
                return new IAbilityAction() {
                    @Override
                    public double getDuration() {
                        return duration;
                    }

                    @Override
                    public void apply(ILevel level, double timePassed) {
                    }
                };
            }
        });

        e1.setAbilityBehaviour(new AbilityBehaviour(abilities) {
            @Override
            public IAbilityAction apply(IEntity<?> user, IEntity<?> target) {
                return abilities.get(0).use(user, target);
            }
        });
        enemies.add(e1);

        ILevel level = new Level(enemies, new ArrayList<>(), new ArrayList<>(), player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);

        Game game = new Game(levels);
        game.update(1.0/60, 1);

        assertEquals(1, game.getActiveAbilityActions().size());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail();
        }
        game.update(1.0, 1);

        assertEquals(0, game.getActiveAbilityActions().size());
    }

    @Test
    public void testPlayerFacingMouse() {
        Player player = EntityFactory.basicPlayer(600, 400);

        ILevel level = new Level(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);

        Game game = new Game(levels);

        Point2D mousePosition = new Point2D(310, 515);
        game.setPlayerFacingMouse(mousePosition);

        Point2D expected = mousePosition.subtract(player.getPosition()).normalize();
        Point2D actual = Utils.vectorFromHeading(player.getShape().getRotation(), 1.0);

        assertEquals(expected.getX(), actual.getX(), 0.00000001);
        assertEquals(expected.getY(), actual.getY(), 0.00000001);
    }

    @Test
    public void testPlayerFacingMouseOnPlayerPosition() {
        Player player = EntityFactory.basicPlayer(600, 400);

        ILevel level = new Level(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), player, 1200, 800);
        List<ILevel> levels = new ArrayList<>();
        levels.add(level);

        Game game = new Game(levels);

        Point2D mousePosition = new Point2D(600, 400);

        double previousFacingDirection = player.getShape().getRotation();
        game.setPlayerFacingMouse(mousePosition);

        assertEquals(previousFacingDirection, player.getShape().getRotation(), 0.0);
    }
}
