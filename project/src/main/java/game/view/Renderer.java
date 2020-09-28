package game.view;

import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import game.model.entity.projectile.Bullet;
import game.model.entity.projectile.IProjectile;
import game.model.level.ILevel;
import game.model.shape2d.ICircle;
import game.model.shape2d.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Renderer implements IRenderer{
    private final HashMap<Class<?>, Color> colors = new HashMap<>();
    private final GraphicsContext graphicsContext;

    private final Rectangle testRect = new Rectangle(40, 40, 0);

    public Renderer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        colors.put(Player.class, Color.rgb(200, 200, 200));
        colors.put(Enemy.class, Color.rgb(100, 200, 150));
        colors.put(Bullet.class, Color.rgb(100, 100, 100));
        colors.put(GraphicsContext.class, Color.rgb(30, 30, 30));
    }


    public void draw(ILevel level) {
        RendererUtils.clear(graphicsContext);

        RendererUtils.setBackgroundColor(graphicsContext, colors.get(graphicsContext.getClass()));

        RendererUtils.drawShape(graphicsContext, colors.get(level.getPlayer().getClass()) ,level.getPlayer().getShape(), level.getPlayer().getPosition());

        for(IProjectile<?> projectile : level.getProjectiles()) {
            //TODO: ICircle cast is temporary solution...
            RendererUtils.drawShape(graphicsContext, colors.get(projectile.getClass()), (ICircle)projectile.getShape(), projectile.getPosition());
        }

        for (Enemy enemy : level.getEnemies()) {
            RendererUtils.drawShape(graphicsContext, colors.get(enemy.getClass()), enemy.getShape(), enemy.getPosition());
            RendererUtils.drawShape(graphicsContext, colors.get(enemy.getClass()), testRect, enemy.getPosition());
        }



        //TODO: remove this (used for testing)
        //RendererUtils.translateRectangle(graphicsContext, testRect);
        //testRect.setRotation(testRect.getRotation() + 1);
    }

}
