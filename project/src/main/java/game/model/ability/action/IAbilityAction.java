package game.model.ability.action;
import game.model.level.ILevel;

public interface IAbilityAction {
    double getDuration();
    void apply(ILevel level, double timePassed);
}
