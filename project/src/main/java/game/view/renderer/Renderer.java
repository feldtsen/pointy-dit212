package game.view.renderer;

import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.player.Player;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.model.shape2d.ICircle;
import game.model.shape2d.Rectangle;
import game.model.shape2d.Triangle;
import game.util.Utils;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Renderer implements IRenderer {
    // A map linking a class of objects to a particular color. Used to render different entities with different
    // colors.
    private final HashMap<Class<?>, Color> colors = new HashMap<>();

    // The graphics object used to draw on screen.
    private final GraphicsContext graphicsContext;

    //TODO: for testing
    private final Rectangle testRect = new Rectangle(100, 100, 0);
    private final Triangle testTriangle = new Triangle(100, 100, 0);

    public Renderer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

        // Initialize different entity classes with different colors
        colors.put(Player.class,          Color.rgb(200, 200, 200));
        colors.put(Enemy.class,           Color.rgb(80, 80, 80));
        colors.put(Bullet.class,          Color.rgb(100, 100, 100));
        colors.put(GraphicsContext.class, Color.rgb(30,  30,  30 ));
    }

    @Override
    public void draw(ILevel level) {
        // Clear the screen
        RendererUtils.clear(graphicsContext);

        // Set background color
        RendererUtils.setBackgroundColor(graphicsContext, colors.get(graphicsContext.getClass()));

        // Render player
        RendererUtils.drawShape(graphicsContext, colors.get(level.getPlayer().getClass()), level.getPlayer().getShape(), level.getPlayer().getPosition());

        // TODO: temporary testing code, to show facing direction of player
        Point2D direction = Utils.vectorFromHeading(level.getPlayer().getShape().getRotation(), level.getPlayer().getShape().getRadius() - 5);
        RendererUtils.drawLine(graphicsContext,
                colors.get(level.getPlayer().getClass()),
                level.getPlayer().getPosition(),
                level.getPlayer().getPosition().add(direction),
                7);

        // Render all projectiles
        for(IProjectile<?> projectile : level.getProjectiles()) {
            //TODO: ICircle cast is temporary solution...
            //TODO: later, all projectiles will not be circles
            RendererUtils.drawShape(graphicsContext, colors.get(projectile.getClass()), (ICircle)projectile.getShape(), projectile.getPosition());
        }

        // Render all enemies
        for (IEnemy enemy : level.getEnemies()) {
            double radians = Utils.heading(enemy.getVelocity());

            testRect.setRotation(radians);
            enemy.getShape().setRotation(radians);
            testTriangle.setRotation(radians);


            //TODO: test for drawing rotated shapes
            RendererUtils.drawShape(graphicsContext, colors.get(enemy.getClass()), testRect, enemy.getPosition());
            RendererUtils.drawShape(graphicsContext, colors.get(enemy.getClass()), enemy.getShape(), enemy.getPosition());
            RendererUtils.drawShape(graphicsContext, colors.get(enemy.getClass()), testTriangle, enemy.getPosition());
        }

    }
}
