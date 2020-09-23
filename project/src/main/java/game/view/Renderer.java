package game.view;

import game.model.IGame;
import game.model.entity.enemy.Enemy;
import game.model.entity.player.Player;
import game.model.level.ILevel;
import javafx.scene.canvas.Canvas;
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

        graphicsContext.setFill(entityColors.get(level.getPlayer().getClass()));
        RendererUtils.drawEntity(graphicsContext, level.getPlayer().getShape(), level.getPlayer().getPosition());

        for (Enemy enemy : level.getEnemies()) {
            graphicsContext.setFill(entityColors.get(enemy.getClass()));
            RendererUtils.drawEntity(graphicsContext, enemy.getShape(), enemy.getPosition());
        }
    }


}
