package game.view.renderer;

import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.Player;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.model.entity.projectile.Missile;
import game.model.level.ILevel;
import game.model.shape2d.*;
import game.util.Utils;
import game.view.IVisitor;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Renderer implements IRenderer, IVisitor  {
    // A map linking a class of objects to a particular color. Used to render different entities with different
    // colors.
    private final HashMap<Class<?>, Color> colors = new HashMap<>();

    // The graphics object used to draw on screen.
    private final GraphicsContext graphicsContext;

    private IEntity entity;

    public Renderer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

        // Initialize different entity classes with different colors
        colors.put(Player.class,          Color.rgb(150, 150, 150));
        colors.put(Enemy.class,           Color.rgb(80, 80, 80));
        colors.put(Bullet.class,          Color.rgb(90, 90, 200));
        colors.put(Missile.class,          Color.rgb(200, 90, 90));
        colors.put(GraphicsContext.class, Color.rgb(30,  30,  30 ));
    }

    @Override
    public void draw(ILevel level) {
        // Clear the screen
        RendererUtils.clear(graphicsContext);

        // Set background color
        RendererUtils.setBackgroundColor(graphicsContext, colors.get(graphicsContext.getClass()));

        // Render player
        RendererUtils.drawCircle(graphicsContext, colors.get(level.getPlayer().getClass()), level.getPlayer().getShape(), level.getPlayer().getPosition());

        Point2D direction = Utils.vectorFromHeading(level.getPlayer().getShape().getRotation(), level.getPlayer().getShape().getRadius() - 5);
        RendererUtils.drawLine(graphicsContext,
                colors.get(level.getPlayer().getClass()),
                level.getPlayer().getPosition(),
                level.getPlayer().getPosition().add(direction),
                7);

        // Render all projectiles
        for(IProjectile<?> projectile : level.getProjectiles()) {
            entity = projectile;
            projectile.getShape().setRotation(Utils.heading(projectile.getVelocity()));
            //RendererUtils.drawShape(graphicsContext, colors.get(projectile.getClass()), projectile.getShape(), projectile.getPosition());
            projectile.getShape().accept(this);
        }

        // Render all enemies
        for (IEnemy enemy : level.getEnemies()) {
            entity = enemy;

            double radians = Utils.heading(enemy.getVelocity());
            enemy.getShape().setRotation(radians);
            enemy.getShape().accept(this);
        }

    }

    @Override
    public void visit(ICircle circle) {
        RendererUtils.drawCircle(graphicsContext, colors.get(entity.getClass()), circle, entity.getPosition());
    }

    @Override
    public void visit(IRectangle rectangle) {
        RendererUtils.drawRectangle(graphicsContext, colors.get(entity.getClass()), rectangle, entity.getPosition());
    }

    @Override
    public void visit(ITriangle triangle) {
        RendererUtils.drawTriangle(graphicsContext, colors.get(entity.getClass()), triangle, entity.getPosition());
    }
}
