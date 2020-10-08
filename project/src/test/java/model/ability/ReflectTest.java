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
    double control;
    Point2D vector;

    @Before
    public void init() {
        // Create ability reflect
        IAbility reflect = new Reflect(10, Math.PI / 2, 100, control, 0.1, 1000);

        List<IProjectile<?>> projectiles = new ArrayList<>();

        projectile = new Bullet(new Point2D(500, 500), 10, 10, 10, 1, new Point2D(50, 50));
        level.getProjectiles().add(projectile);
        player = new Player(new Point2D(500, 500), 1, 1, 3, 0);

        player.addAbility(reflect);
        level = new Level(new ArrayList<>(), projectiles, new ArrayList<>(), player, 1200, 800);

    }

    private void reflect() {
        IAbilityAction abilityAction = player.activateAbility(0);
        abilityAction.apply(level, 0);
    }

    @Test
    public void projectileInRangeZeroControl() {

        control = 0;
        level.getProjectiles().get(0).setPosition(new Point2D(515,500));
        Point2D bulletPosition = level.getProjectiles().get(0).getPosition();
        Point2D playerPosition = player.getPosition();

        // Vector between player and projectile
        Point2D vector = bulletPosition.subtract(playerPosition);

        double dir = Utils.heading(vector);
        player.getShape().setRotation(dir);

        reflect();

        Point2D newVelocity = newProjectileVelocity(vector);
        assertEquals(newVelocity.getX(), level.getProjectiles().get(0).getVelocity().getX(),0.0);
        assertEquals(newVelocity.getY(), level.getProjectiles().get(0).getVelocity().getY(), 0.0);
    }

    private Point2D newProjectileVelocity(Point2D vector){

        Point2D n = vector.normalize();

        // Vector representing the direction the player is facing
        Point2D f = Utils.vectorFromHeading(player.getShape().getRotation(), 1);

        Point2D newVelocityDirection = n.interpolate(f, control);

        return Utils.setMagnitude(newVelocityDirection, projectile.getVelocity().magnitude());
    }
}
