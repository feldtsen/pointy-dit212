package game.model.ability.action;

import game.model.entity.IEntity;
import game.model.level.ILevel;

// An ability action is a set of instructions for how the contents
// of a level should be manipulated. The ability action is limited to a set amount of time.
public interface IAbilityAction {
    double getDuration();
    // The apply method will apply all changes, possibly adjusted depending on how much time has passed since activation.
    void apply(ILevel level, double timePassed);

    // This method is called when the IAbilityAction is done, for cleanup
    void onFinished(ILevel level);

    // Returns the user of the action
    IEntity<?> getUser();
}
