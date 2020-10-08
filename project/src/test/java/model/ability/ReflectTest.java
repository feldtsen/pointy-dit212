package model.ability;

import game.model.ability.IAbility;
import game.model.ability.Reflect;
import game.model.ability.action.IAbilityAction;
import game.model.entity.player.IPlayer;
import game.model.entity.player.Player;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.model.level.Level;
import game.util.Utils;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReflectTest {

    IProjectile<?> projectile;
    IPlayer player;
    ILevel level;

    @Before
    public void init() {
        // Create ability reflect

        List<IProjectile<?>> projectiles = new ArrayList<>();

        projectile = new Bullet(new Point2D(500, 500), 10, 10, 10, 1, new Point2D(50, 50));
        projectiles.add(projectile);
        player = new Player(new Point2D(500, 500), 1, 1, 3, 0);

        level = new Level(new ArrayList<>(), projectiles, new ArrayList<>(), player, 1200, 800);

    }

    private void reflect() {
        IAbilityAction abilityAction = player.activateAbility(0);
        abilityAction.apply(level, 0);
    }

    @Test
    public void projectileInRangeZeroControl() {

        double control = 0;
        testSetup(control, new Point2D(515,500));

        Point2D bulletPosition = level.getProjectiles().get(0).getPosition();
        Point2D playerPosition = player.getPosition();

        // Vector between player and projectile
        Point2D vector = bulletPosition.subtract(playerPosition);

        double dir = Utils.heading(vector);
        player.getShape().setRotation(dir);

        reflect();

        Point2D newVelocity = newProjectileVelocity(vector, control);
        assertEquals(newVelocity.getX(), level.getProjectiles().get(0).getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), level.getProjectiles().get(0).getVelocity().getY(), 0.0);
    }

    @Test
    public void projectileInRangeSomeControl() {
        double control = 0.5;
        testSetup(control, new Point2D(515,500));

        Point2D bulletPosition = level.getProjectiles().get(0).getPosition();
        Point2D playerPosition = player.getPosition();

        // Vector between player and projectile
        Point2D vector = bulletPosition.subtract(playerPosition);

        double dir = Utils.heading(vector);
        player.getShape().setRotation(dir);

        reflect();

        Point2D newVelocity = newProjectileVelocity(vector, control);
        assertEquals(newVelocity.getX(), level.getProjectiles().get(0).getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), level.getProjectiles().get(0).getVelocity().getY(), 0.0);
    }

    @Test
    public void projectileInRangeFullControl() {
        double control = 1.0;
        testSetup(control, new Point2D(515,500));

        Point2D bulletPosition = level.getProjectiles().get(0).getPosition();
        Point2D playerPosition = player.getPosition();

        // Vector between player and projectile
        Point2D vector = bulletPosition.subtract(playerPosition);

        double dir = Utils.heading(vector);
        player.getShape().setRotation(dir);

        reflect();

        Point2D newVelocity = newProjectileVelocity(vector, control);
        assertEquals(newVelocity.getX(), level.getProjectiles().get(0).getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), level.getProjectiles().get(0).getVelocity().getY(), 0.0);
    }
    @Test
    public void maximumReflectRange() {

        double control = 0;
        testSetup(control, new Point2D(600,500));

        Point2D bulletPosition = level.getProjectiles().get(0).getPosition();
        Point2D playerPosition = player.getPosition();

        // Vector between player and projectile
        Point2D vector = bulletPosition.subtract(playerPosition);

        double dir = Utils.heading(vector);
        player.getShape().setRotation(dir);

        reflect();

        Point2D newVelocity = newProjectileVelocity(vector, control);
        assertEquals(newVelocity.getX(), level.getProjectiles().get(0).getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), level.getProjectiles().get(0).getVelocity().getY(), 0.0);
    }
    @Test
    public void projectileNotInRange() {

        double control = 0;
        testSetup(control, new Point2D(50,50));

        Point2D bulletPosition = level.getProjectiles().get(0).getPosition();
        Point2D playerPosition = player.getPosition();

        // Vector between player and projectile
        Point2D vector = bulletPosition.subtract(playerPosition);

        double dir = Utils.heading(vector);
        player.getShape().setRotation(dir);
        double vx =  level.getProjectiles().get(0).getVelocity().getX();
        double vy =  level.getProjectiles().get(0).getVelocity().getY();
        reflect();

        assertEquals(vx, level.getProjectiles().get(0).getVelocity().getX(),0.0);
        assertEquals(vy, level.getProjectiles().get(0).getVelocity().getY(), 0.0);
    }

    private Point2D newProjectileVelocity(Point2D vector, double control){

        Point2D n = vector.normalize();

        // Vector representing the direction the player is facing
        Point2D f = Utils.vectorFromHeading(player.getShape().getRotation(), 1);

        Point2D newVelocityDirection = n.interpolate(f, control);

        return Utils.setMagnitude(newVelocityDirection, projectile.getVelocity().magnitude());
    }
    private void testSetup(double control, Point2D vector){
        level.getProjectiles().get(0).setPosition(vector);
        IAbility reflect = new Reflect(9, Math.PI / 2, 100, control, 0.1, 1000);
        player.addAbility(reflect);
    }
}
