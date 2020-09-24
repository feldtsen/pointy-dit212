package game.view;

import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import game.model.level.ILevel;
import game.model.shape2d.Rectangle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Renderer implements IRenderer{
    private final HashMap<Class<?>, Color> entityColors = new HashMap<>();
    private final GraphicsContext graphicsContext;

    public Renderer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        entityColors.put(Player.class, Color.rgb(200, 200, 200));
        entityColors.put(Enemy.class, Color.rgb(100, 200, 150));
    }


    public void draw(ILevel level) {
        RendererUtils.clear(graphicsContext);

        RendererUtils.setBackgroundColor(graphicsContext, Color.rgb(30, 30, 30));

        RendererUtils.drawShape(graphicsContext, entityColors.get(level.getPlayer().getClass()) ,level.getPlayer().getShape(), level.getPlayer().getPosition());

        for (Enemy enemy : level.getEnemies()) {
            RendererUtils.drawShape(graphicsContext, entityColors.get(enemy.getClass()), enemy.getShape(), enemy.getPosition());
        }
    }

}
