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
    private final HashMap<Class<?>, Color> shapeColors = new HashMap<>();
    private final GraphicsContext graphicsContext;

    private final Rectangle testRect = new Rectangle(40, 40, 0);

    public Renderer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        shapeColors.put(Player.class, Color.rgb(200, 200, 200));
        shapeColors.put(Enemy.class, Color.rgb(100, 200, 150));
        shapeColors.put(GraphicsContext.class, Color.rgb(30, 30, 30));
    }


    public void draw(ILevel level) {
        RendererUtils.clear(graphicsContext);

        RendererUtils.setBackgroundColor(graphicsContext, shapeColors.get(graphicsContext.getClass()));

        RendererUtils.drawShape(graphicsContext, shapeColors.get(level.getPlayer().getClass()) ,level.getPlayer().getShape(), level.getPlayer().getPosition());

        for (Enemy enemy : level.getEnemies()) {
            RendererUtils.drawShape(graphicsContext, shapeColors.get(enemy.getClass()), enemy.getShape(), enemy.getPosition());

        }


        //TODO: remove this (used for testing)
        RendererUtils.translate(graphicsContext, testRect);
        testRect.setRotation(testRect.getRotation() + 1);
    }

}
