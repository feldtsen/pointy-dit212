package game;

import game.controller.gameLoop.GameLoop;
import game.model.Game;
import game.model.ability.Ability;
import game.model.ability.IAbility;
import game.model.ability.action.IAbilityAction;
import game.model.behavior.ability.AbilityBehaviour;
import game.model.behavior.ability.SingleAbilityBehavior;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.movable.MovableEntity;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.player.IPlayer;
import game.model.entity.player.Player;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.model.level.Level;
import game.model.shape2d.Circle;
import game.model.shape2d.ICircle;
import game.services.EntityFactory;
import game.util.Utils;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
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
    public void before()  {
        IPlayer player = new Player(new Point2D(500, 500), 20, 2500, 1000,0);
        IEnemy enemy = EntityFactory.basicEnemy(500,500,player,5);
        List<IEnemy> enemies = new ArrayList<>(List.of(enemy));

        List<IProjectile<?>> projectiles = new ArrayList<>();
        List<IObstacle> obstacles = new ArrayList<>();
        ILevel level = new Level(enemies,projectiles,obstacles, player,1200,675);

        game = new Game();
        game.setLevel(level);
   
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

        game.update(1.0/60, 1);

        assertEquals(0, game.getCurrentLevel().getPlayer().getHitPoints() );

    }
    @Test
    public void testUpdatePlayerSameStrength(){

        game.getCurrentLevel().getPlayer().setStrength(5);
        game.update(1.0/60, 1);

        assertEquals(1, game.getCurrentLevel().getPlayer().getHitPoints());

    }

    @Test
    public void testUpdatePlayerMoreStrength(){
        game.getCurrentLevel().getPlayer().setStrength(10);
        game.update(1.0/60, 1);

        assertEquals(1, game.getCurrentLevel().getPlayer().getHitPoints() );

    }


    @Test
    public void testAbilityAddedToAbilityActionList() {

        List<IAbility> abilities = new ArrayList<>();
        abilities.add(new Ability(1) {
            @Override
            public IAbilityAction createAction(IEntity<?> user, IEntity<?> target) {
                return new IAbilityAction() {
                    @Override
                    public double getDuration() {
                        return 1;
                    }

                    @Override
                    public void apply(ILevel level, double timePassed) {
                    }

                    @Override
                    public void onFinished(ILevel level) {
                    }

                    @Override
                    public IEntity<?> getUser() {
                        return null;
                    }
                };
            }
        });
        IEnemy enemy = game.getCurrentLevel().getEnemies().get(0);
        enemy.setAbilityBehaviour(new AbilityBehaviour(abilities) {
            @Override
            public IAbilityAction apply(IEntity<?> user, IEntity<?> target) {
                return abilities.get(0).use(user, target);
            }
        });


        game.update(1, 1);

        assertEquals(1, game.getActiveAbilityActions().size());
    }


    @Test
    public void testAbilityRemovedFromAbilityActionList() {
        double duration = 0.5;

        Ability ability = new Ability(GameLoop.SECOND * 2) {
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

                    @Override
                    public void onFinished(ILevel level) {
                    }

                    @Override
                    public IEntity<?> getUser() {
                        return null;
                    }
                };
            }
        };

        IEnemy enemy = game.getCurrentLevel().getEnemies().get(0);
        enemy.setPosition(new Point2D(200,200));
        enemy.setAbilityBehaviour(new SingleAbilityBehavior(ability));

        game.update(1.0/60, 1);

        assertEquals(1, game.getActiveAbilityActions().size());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            fail();
        }

        game.update(1.0/60, 1);

        assertEquals(0, game.getActiveAbilityActions().size());
    }

    @Test
    public void testPlayerFacingMouse() {

        Point2D mousePosition = new Point2D(310, 515);
        game.setPlayerFacingPosition(mousePosition);

        game.update(1.0, 1.0);
        IPlayer player = game.getCurrentLevel().getPlayer();
        Point2D expected = mousePosition.subtract(player.getPosition()).normalize();
        Point2D actual = Utils.vectorFromHeading(player.getShape().getRotation(), 1.0);

        assertEquals(expected.getX(), actual.getX(), 0.00000001);
        assertEquals(expected.getY(), actual.getY(), 0.00000001);
    }

    @Test
    public void testPlayerFacingMouseOnPlayerPosition() {

        Point2D mousePosition = new Point2D(500, 500);

        IPlayer player = game.getCurrentLevel().getPlayer();
        double previousFacingDirection = player.getShape().getRotation();
        game.setPlayerFacingPosition(mousePosition);

        game.update(1.0, 1.0);

        assertEquals(previousFacingDirection, player.getShape().getRotation(), 0.0);
    }
}
