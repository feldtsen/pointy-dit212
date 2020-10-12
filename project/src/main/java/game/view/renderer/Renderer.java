package game.view.renderer;

import game.controller.Action;
import game.controller.event.AbilityActionEventListener;
import game.controller.event.IAbilityActionEvent;
import game.controller.gameLoop.GameLoop;
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
import game.view.IShapeVisitor;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Renderer implements IRenderer, IShapeVisitor, AbilityActionEventListener {
    public abstract static class Effect {
        private final double effectDuration;

        abstract void render(IAbilityAction action, double time);

        public Effect(double effectDuration) {
            this.effectDuration = effectDuration;
        }

        public double getEffectDuration() {
            return effectDuration;
        }
    }

    // A map linking a class of objects to a particular color. Used to render different entities with different
    // colors.
    private final HashMap<Class<?>, Color> colors = new HashMap<>();

    // A map linking an effect to an ability action
    private final HashMap<Class<? extends IAbilityAction>, Effect> abilityEffects = new HashMap<>();

    // A list with all currently active abilities stored for rendering effects
    private final List<IAbilityAction> abilityActions = new ArrayList<>();

    // A list with all activation times of all active abilities
    private final List<Long> activationTimes = new ArrayList<>();

    // The graphics object used to draw on screen.
    private final GraphicsContext graphicsContext;

    private IEntity<?> entity;

    public Renderer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

        // Initialize different entity classes with different colors
        colors.put(Player.class, Color.rgb(255, 255, 255));
        colors.put(Enemy.class, Color.rgb(167, 173, 186, .96));
        colors.put(Bullet.class, Color.rgb(96, 106, 116));
        colors.put(Missile.class, Color.rgb(153, 163, 156));
        colors.put(GraphicsContext.class, Color.rgb(52, 61, 70));

        abilityEffects.put(Dash.DashAction.class, createDashEffect());
        abilityEffects.put(Shockwave.ShockwaveAction.class, createShockwaveEffect());

    }

    private Effect createDashEffect() {
        return new Effect(0) {
            @Override
            void render(IAbilityAction action, double time) {

            }
        };

        //TODO
    }


    private Effect createShockwaveEffect() {
        double radius = 1000; //TODO how to get radius?


        return new Effect(0.2) {
            @Override
            void render(IAbilityAction action, double time) {

                Point2D position = action.getUser().getPosition();
                ICircle circle = new Circle(radius * time);
                RendererUtils.drawRing(graphicsContext, colors.get(action.getUser().getClass()), circle, position);
            }
        };
    }

    @Override
    public void drawEntities(ILevel level) {
        // Clear the screen
        RendererUtils.clear(graphicsContext);

        // Set background color
        RendererUtils.setBackgroundColor(graphicsContext, colors.get(graphicsContext.getClass()));

        // Render player
        entity = level.getPlayer();
        setRotation(level.getPlayer().getVelocity());
        entity.getShape().acceptShapeVisitor(this);

        // Render all projectiles
        for(IProjectile<?> projectile : level.getProjectiles()) {
            entity = projectile;
            setRotation(projectile.getVelocity());
            projectile.getShape().acceptShapeVisitor(this);
        }

        // Render all enemies
        for (IEnemy enemy : level.getEnemies()) {
            entity = enemy;

            setRotation(enemy.getVelocity());
            enemy.getShape().acceptShapeVisitor(this);
        }
    }

    public void drawAbilities() {
        for(int i = abilityActions.size() - 1; i >= 0; i--) {
            IAbilityAction action = abilityActions.get(i);
            double time = (double)(System.nanoTime() - activationTimes.get(i)) / GameLoop.SECOND;
            Effect effect = abilityEffects.get(action.getClass());
            if(effect == null) continue;

            if(time > effect.getEffectDuration()){
                abilityActions.remove(i);
                activationTimes.remove(i);
                continue;
            }
            effect.render(action, time);
        }
    }

    @Override
    public void onAction(IAbilityActionEvent event) {
        if (event.getType() == IAbilityActionEvent.Type.ACTIVATED && abilityEffects.containsKey(event.getAction().getClass())){
            abilityActions.add(event.getAction());
            activationTimes.add(System.nanoTime());
        }
    }

    @Override
    public void clearAbilities() {
        abilityActions.clear();
        activationTimes.clear();
    }

    @Override
    public void visit(ICircle circle) {
        RendererUtils.drawCircle(graphicsContext,
                colors.get(entity.getClass()),
                circle,
                entity.getPosition()
        );
    }

    @Override
    public void visit(IRectangle rectangle) {
        RendererUtils.drawRectangle(graphicsContext, colors.get(entity.getClass()), rectangle, entity.getPosition());
    }

    @Override
    public void visit(ITriangle triangle) {
        RendererUtils.drawTriangle(graphicsContext, colors.get(entity.getClass()), triangle, entity.getPosition());
    }

    private void setRotation(Point2D velocity) {
       entity.getShape().setRotation(Utils.heading(velocity));
    }

}
