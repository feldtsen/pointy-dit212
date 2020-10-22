/*
 * Authors: Joachim Pedersen, Mattias Oom, Anton Hildingsson, Erik Magnusson, Simon Genne
 *
 * The renderer handles drawing to a GraphicsContext which is supplied by the GameCanvas.
 * This is the core view class which does all view-related interaction with javaFX through
 * a RenderUtils class. This is done to facilitate the changing of graphics library, if necessary.
 */

package game.view.renderer;

import game.controller.event.IAbilityActionEventListener;
import game.controller.event.IAbilityActionEvent;
import game.controller.gameLoop.GameLoop;
import game.model.ability.Dash;
import game.model.ability.Reflect;
import game.model.ability.Shockwave;
import game.model.ability.action.IAbilityAction;
import game.model.entity.IEntity;
import game.model.entity.enemy.Enemy;
import game.model.entity.enemy.IEnemy;
import game.model.entity.obstacle.IObstacle;
import game.model.entity.obstacle.MovingWall;
import game.model.entity.obstacle.Spikes;
import game.model.entity.obstacle.Wall;
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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Renderer implements IRenderer, IShapeVisitor, IAbilityActionEventListener {
    // Class for defining a effect which is displayed when certain ability actions are activated
    public abstract static class Effect {
        // The duration of the effect
        private final double effectDuration;

        // Render method, called as long as the effect is active
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
        colors.put(Player.class,          Color.rgb(255, 255, 255));
        colors.put(Enemy.class,           Color.rgb(167, 173, 186, .96));
        colors.put(Bullet.class,          Color.rgb(96, 106, 116));
        colors.put(Missile.class,         Color.rgb(153, 163, 156));
        colors.put(Wall.class,            Color.rgb(96, 106, 116));
        colors.put(MovingWall.class,      Color.rgb(96, 106, 116));
        colors.put(Spikes.class,          Color.rgb(135, 72, 70));
        colors.put(GraphicsContext.class, Color.rgb(52, 61, 70));

        // Initialize different ability actions with effects
        abilityEffects.put(Dash.DashAction.class,           createDashEffect());
        abilityEffects.put(Shockwave.ShockwaveAction.class, createShockwaveEffect());
        abilityEffects.put(Reflect.ReflectAction.class,     createReflectEffect());
    }

    // No effect for dash
    private Effect createDashEffect() {
        return new Effect(0) {
            @Override
            void render(IAbilityAction action, double time) {
            }
        };
    }

    // effect for reflect
    private Effect createReflectEffect(){
     return new Effect(0.2) {
         // The radius of the effect
         final double radius = 1000;
         @Override
         void render(IAbilityAction action, double time) {
             // The position of the user
             Point2D position = action.getUser().getPosition();

             // The shape used to draw the effect
             ICircle circle = new Circle(radius * time);

             // Rotation of circle
             circle.setRotation(action.getUser().getShape().getRotation() + (3 * Math.PI)/4);

             // Stops is used to define a gradient effect
             Stop[] stops = new Stop[]{new Stop(0, Color.TRANSPARENT), new Stop(1, colors.get(action.getUser().getClass()))};

             // Gradient effect used to draw effect
             LinearGradient linearGradient = new LinearGradient(
                     0,
                     .1,
                     .1,
                     0,
                     true,
                     CycleMethod.NO_CYCLE,
                     stops
             );
             // Draw arc using gradient
             RendererUtils.drawArc(graphicsContext, linearGradient, circle, position, Math.PI/2);
         }
     };
    }


    // Chockwave effect
    private Effect createShockwaveEffect() {
        return new Effect(0.2) {
            final double radius = 1000;
            @Override
            void render(IAbilityAction action, double time) {
                // Position of user
                Point2D position = action.getUser().getPosition();
                // Circle shape used to  draw effect
                ICircle circle = new Circle(radius * time);
                // Draws a ring
                RendererUtils.drawRing(graphicsContext, colors.get(action.getUser().getClass()), circle, position);
            }
        };
    }

    @Override
    public void drawEntities(ILevel level) {
        graphicsContext.save();
        // Clear the screen
        RendererUtils.clear(graphicsContext);

        // Set background color
        RendererUtils.setBackgroundColor(graphicsContext, colors.get(graphicsContext.getClass()));

        // Render player
        entity = level.getPlayer();
        setRotation(level.getPlayer().getFacingDirection());
        entity.getShape().acceptShapeVisitor(this);


        // Draws a triangle on top of the player to be able to see the direction the player is facing
        RendererUtils.drawTriangle(
                graphicsContext,
                colors.get(level.getPlayer().getClass()).darker(),
                new Triangle(
                        entity.getShape().getWidth() * .4,
                        entity.getShape().getHeight() * .8,
                        entity.getShape().getRotation()
                ),
                level.getPlayer().getPosition()
        );

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

        // Render all obstacles
        for (IObstacle obstacle : level.getObstacles()) {
            graphicsContext.save();
            entity = obstacle;
            //RendererUtils.setCorrectObstacleRotation(graphicsContext, obstacle.getShape(), obstacle.getPosition());
            RendererUtils.setCorrectObstacleRotation(graphicsContext, obstacle.getShape(), obstacle.getPosition());
            // TODO: add rotation
            entity.getShape().acceptShapeVisitor(this);
            graphicsContext.restore();
        }
        // Render all enemies
        for (IEnemy enemy : level.getEnemies()) {
            entity = enemy;

            setRotation(enemy.getVelocity());
            enemy.getShape().acceptShapeVisitor(this);
        }
        graphicsContext.restore();
    }

    // Renders abilities to the screen
    public void drawAbilities() {
        // loop backwards to enable deleting ability effects while iterating
        for(int i = abilityActions.size() - 1; i >= 0; i--) {
            IAbilityAction action = abilityActions.get(i);

            // Calculate the time passed
            double time = (double)(System.nanoTime() - activationTimes.get(i)) / GameLoop.SECOND;
            Effect effect = abilityEffects.get(action.getClass());
            if(effect == null) continue;

            // Remove effect if duration has passed
            if(time > effect.getEffectDuration()){
                abilityActions.remove(i);
                activationTimes.remove(i);
                continue;
            }

            // Render
            effect.render(action, time);
        }
    }

    // Method called when the model creates an AbilityActionEvent.
    @Override
    public void onAction(IAbilityActionEvent event) {
        // If the ability action is activated and if the there is an effect defined for the action, continue
        if (event.getType() == IAbilityActionEvent.Type.ACTIVATED && abilityEffects.containsKey(event.getAction().getClass())){
            // Add to actions list and set activation time
            abilityActions.add(event.getAction());
            activationTimes.add(System.nanoTime());
        }
    }

    // Clear all ability effects
    @Override
    public void clearAbilities() {
        abilityActions.clear();
        activationTimes.clear();
    }

    // Clear entire canvas
    @Override
    public void clearCanvas() {
        RendererUtils.clear(graphicsContext);
    }

    // Visitor pattern implementations below, used to draw different shapes to the screen
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

    // Sets rotation of an entity
    private void setRotation(Point2D velocity) {
       entity.getShape().setRotation(Utils.heading(velocity));
    }

}
