package game.model.ability.action;
import game.model.level.ILevel;

public interface IAbilityAction {
    double getRadius();
    double getDuration();
    void apply(ILevel level, double timePassed);
}
