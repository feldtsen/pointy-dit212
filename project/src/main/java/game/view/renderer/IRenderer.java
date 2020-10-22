/*
 * Authors: Mattias Oom, Anton Hildingsson, Simon Genne, Joachim Pedersen
 *
 * Abstraction for creating a renderer which can draw entities and ability effects
 * to a canvas.
 */

package game.view.renderer;

import game.model.ability.action.IAbilityAction;
import game.model.level.ILevel;

import java.util.List;

// A view for rendering a single level
public interface IRenderer {
    void drawEntities(ILevel level);
    void drawAbilities();
    void clearAbilities();
    void clearCanvas();
}
