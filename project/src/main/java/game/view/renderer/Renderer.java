package game.view.renderer;

import game.model.ability.Dash;
import game.model.ability.Shockwave;
import game.model.ability.action.IAbilityAction;
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
import java.util.List;

public class Renderer implements IRenderer, IVisitor  {
    public interface Effect {
        void render(IAbilityAction action, double time);
    }

    // A map linking a class of objects to a particular color. Used to render different entities with different
    // colors.
    private final HashMap<Class<?>, Color> colors = new HashMap<>();

    // A map linking an effect to an ability action
    private final HashMap<Class<? extends IAbilityAction>, Effect> abilityEffects = new HashMap<>();

    // The graphics object used to draw on screen.
    private final GraphicsContext graphicsContext;

    private IEntity<?> entity;

    public Renderer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

        // Initialize different entity classes with different colors
        colors.put(Player.class,          Color.rgb(150, 150, 150));
        colors.put(Enemy.class,           Color.rgb(80, 100, 80));
        colors.put(Bullet.class,          Color.rgb(90, 90, 200));
        colors.put(Missile.class,         Color.rgb(200, 90, 90));
        colors.put(GraphicsContext.class, Color.rgb(30,  30,  30 ));

        abilityEffects.put(Dash.DashAction.class, createDashEffect());
        abilityEffects.put(Shockwave.ShockwaveAction.class, createShockwaveEffect());
    }

    private Effect createDashEffect() {
        return (action, time) -> {
           //TODO
        };
    }

    private Effect createShockwaveEffect() {
        double radius = 100; //TODO how to get radius?
        return (action, time) -> {
            Point2D position = action.getUser().getPosition();
            ICircle circle = new Circle(radius * time);
            RendererUtils.drawRing(graphicsContext, Color.rgb(150, 120, 140), circle, position);
        };
    }

    @Override
    public void drawEntities(ILevel level) {
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

    public void drawAbilities(List<IAbilityAction> actions, List<Double> times) {
        for(int i = 0; i < actions.size(); i++) {
            IAbilityAction action = actions.get(i);
            double time = times.get(i);

            Effect effect = abilityEffects.get(action.getClass());
            if(effect != null) {
                effect.render(action, time);
            }
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
