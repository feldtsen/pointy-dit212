package game.view.renderer;

import game.model.ability.action.IAbilityAction;
import game.model.level.ILevel;

import java.util.List;

// A view for rendering a single level
public interface IRenderer {
    void drawEntities(ILevel level);
    void drawAbilities(List<IAbilityAction> actions, List<Double> times);
}
